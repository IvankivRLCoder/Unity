package com.example.service.impl;

import com.example.dao.TaskDao;
import com.example.dao.UserDao;
import com.example.dao.UserTaskDao;
import com.example.dto.apiKey.ApiKeyDto;
import com.example.dto.task.CreatedTaskDto;
import com.example.dto.task.GetTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.user.MainTaskUserDto;
import com.example.dto.user.MainUserDto;
import com.example.dto.user.UpdateUserDto;
import com.example.dto.usertask.UserTaskDto;
import com.example.error.*;
import com.example.model.Status;
import com.example.model.Task;
import com.example.model.User;
import com.example.model.UserTask;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final TaskDao taskDao;

    private final UserTaskDao userTaskDao;

    private final ModelMapper modelMapper;

    private final EncodingService encodingService;

    @Override
    public MainUserDto getUserById(int id) {
        return modelMapper.map(getById(id), MainUserDto.class);
    }

    @Override
    public List<MainUserDto> getAllUsers() {
        return userDao.getAll()
                .stream()
                .map(user -> modelMapper.map(user, MainUserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(int id, ApiKeyDto apiKeyDto) {
        id = getByApiKey(apiKeyDto.getApiKey());
        User user = getById(id);
        userDao.delete(user);
    }

    @Override
    public MainUserDto updateUser(UpdateUserDto userDto) {
        int id = getByApiKey(userDto.getApiKey());
        User oldUser = getById(id);
        String photo = userDto.getPhoto();
        String fileName = UUID.randomUUID().toString();
        File photoFile = new File("Unity/src/main/resources/static/image/" + fileName + ".jpg");
        encodingService.decodeImage(photo, photoFile.getAbsolutePath());
        oldUser.setPhoto(fileName);
        oldUser.setFirstName(userDto.getFirstName());
        oldUser.setLastName(userDto.getLastName());
        oldUser.setAboutUser(userDto.getAboutUser());
        return modelMapper.map(userDao.update(oldUser), MainUserDto.class);
    }

    @Override
    public List<MainTaskUserDto> getAllTasksByUserId(int id) {
        return userDao.getById(id).getParticipatedTasks()
                .stream()
                .map(userTask -> modelMapper.map(userTask, MainTaskUserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MainTaskUserDto takePartInTask(int userId, int taskId, UserTaskDto userTaskDto) {
        userId = getByApiKey(userTaskDto.getApiKey());
        UserTask userTask = modelMapper.map(userTaskDto, UserTask.class);
        User participant = getById(userId);
        Task participatedTask = getTaskById(taskId);
        userTask.setUser(participant);
        userTask.setTask(participatedTask);
        userTask.setParticipationDate(LocalDate.now());

        if (!userTask.getTask().isActive()) {
            throw new TaskIsNotActiveException("Task is not active.");
        }

        User creator = userTask.getTask().getCreator();
        if (creator.getId() == participant.getId()) {
            throw new UserIsCreatorException("Creator cannot participate in his own task.");
        }

        Optional<User> optionalUser = userTask.getTask().getUserTasks()
                .stream()
                .map(UserTask::getUser)
                .filter(x -> participant.getId() == x.getId())
                .findAny();
        if (optionalUser.isPresent()) {
            throw new UserIsParticipantAlreadyException("User is already participant.");
        }

        long participants = userTask.getTask().getUserTasks()
                .stream()
                .filter(UserTask::isApproved)
                .count();
        if (participants + 1 > userTask.getTask().getPossibleNumberOfParticipants()) {
            throw new OverflowingTaskException("Task is full of participants.");
        }

        return modelMapper.map(userTaskDao.save(userTask), MainTaskUserDto.class);
    }

    @Override
    public MainUserTaskDto approveUserForTask(int userId, int taskId, boolean approved, ApiKeyDto apiKeyDto) {
        int id = getByApiKey(apiKeyDto.getApiKey());
        Task task = getTaskById(taskId);

        if (task.getStatus() == Status.DONE) {
            throw new TaskDoneException("Task already done");
        }

        if (task.getCreator().getId() != id) {
            throw new BadCredentialsException("This user can not approve");
        }

        boolean isParticipant = task.getUserTasks()
                .stream()
                .anyMatch(userTask -> userTask.getUser().getId() == userId);
        if (!isParticipant) {
            throw new EntityNotFountException("User is not participant: " + userId);
        }

        if (approved) {
            long participants = task.getUserTasks()
                    .stream()
                    .filter(UserTask::isApproved)
                    .count();
            if (participants == task.getPossibleNumberOfParticipants()) {
                throw new OverflowingTaskException("Task is full of participants.");
            }
        }

        UserTask userTask = getByUserIdAndTaskId(userId, taskId);
        userTask.setApproved(approved);
        return modelMapper.map(userTaskDao.update(userTask), MainUserTaskDto.class);
    }

    @Override
    public List<CreatedTaskDto> getAllCreatedTasks(int id) {
        User user = getById(id);
        return user.getCreatedTasks()
                .stream()
                .map(task -> modelMapper.map(task, CreatedTaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetTaskDto> getDoneTasks(int id) {
        User user = getById(id);
        return user.getParticipatedTasks()
                .stream()
                .filter(task -> task.getTask().getStatus() == Status.DONE && task.isApproved())
                .map(userTask -> modelMapper.map(userTask.getTask(), GetTaskDto.class))
                .collect(Collectors.toList());
    }

    private UserTask getByUserIdAndTaskId(int userId, int taskId) {

        UserTask userTask;
        try {
            userTask = userTaskDao.getByUserAndTask(userId, taskId);
        } catch (NoResultException exception) {
            throw new EntityNotFountException("User is not found with id = " + userId + " or task with id = " + taskId);
        }

        return userTask;
    }

    private User getById(int id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new EntityNotFountException("User is not found with id = " + id);
        }
        return user;
    }

    private Task getTaskById(int id) {
        Task task = taskDao.getById(id);
        if (task == null) {
            throw new EntityNotFountException("Task is not found with id = " + id);
        }
        return task;
    }

    public int getByApiKey(String apiKey) {
        User user;
        try {
            user = userDao.getByApiKey(encodingService.encode(apiKey));
        } catch (NoResultException | EmptyResultDataAccessException exception) {
            throw new BadCredentialsException("API key is invalid");
        }
        return user.getId();
    }

}