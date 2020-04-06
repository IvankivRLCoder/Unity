package com.example.service.impl;

import com.example.dao.TaskDao;
import com.example.dao.UserDao;
import com.example.dao.UserTaskDao;
import com.example.dto.task.CreatedTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.task.TaskDto;
import com.example.dto.user.ApiKeyDto;
import com.example.dto.user.MainTaskUserDto;
import com.example.dto.user.MainUserDto;
import com.example.dto.user.UserDto;
import com.example.dto.usertask.UserTaskDto;
import com.example.error.*;
import com.example.model.Task;
import com.example.model.User;
import com.example.model.UserTask;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final TaskDao taskDao;

    private final UserTaskDao userTaskDao;

    private final ModelMapper modelMapper;

    @Override
    public MainUserDto getUserById(int id) {
        return modelMapper.map(getById(id), MainUserDto.class);
    }

    @Override
    public List<MainUserDto> getAllUsers() {
        return userDao.getAll().stream().map(user -> modelMapper.map(user, MainUserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(int id, ApiKeyDto apiKeyDto) {
        id = getByApiKey(apiKeyDto.getApiKey());
        User user = getById(id);
        userDao.delete(user);
    }

    @Override
    public MainUserDto updateUser(UserDto userDto, int id) {
        id = getByApiKey(userDto.getApiKey());
        User oldUser = getById(id);
        User newUser = modelMapper.map(userDto, User.class);
        oldUser.setBlocked(newUser.isBlocked());
        oldUser.setDateOfBirth(newUser.getDateOfBirth());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setName(newUser.getName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setTrustLevel(newUser.getTrustLevel());

        return modelMapper.map(userDao.update(oldUser), MainUserDto.class);
    }

    @Override
    public List<MainTaskUserDto> getAllTasksByUserId(int id) {
        return userDao.getById(id).getParticipatedTasks().stream()
                .map(userTask -> modelMapper.map(userTask, MainTaskUserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MainTaskUserDto takePartInTask(int userId, int taskId, UserTaskDto userTaskDto) {
        int id = getByApiKey(userTaskDto.getApiKey());
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

    //TODO: errorHandling after realization auth
    @Override
    public MainUserTaskDto approveUserForTask(int userId, int taskId, boolean approved, ApiKeyDto apiKeyDto) {
        int id = getByApiKey(apiKeyDto.getApiKey());
        Task task = getTaskById(taskId);
        if(task.getCreator().getId()!=id){
            throw new BadCredentialsException("This user can not approve");
        }
        UserTask userTask = getByUserIdAndTaskId(userId, taskId);
        userTask.setApproved(approved);
        return modelMapper.map(userTaskDao.update(userTask), MainUserTaskDto.class);
    }

    @Override
    public List<CreatedTaskDto> getAllCreatedTasks(ApiKeyDto apiKeyDto) {
        int id = getByApiKey(apiKeyDto.getApiKey());
        User user = getById(id);
        return user.getCreatedTasks().stream()
                .map(task->modelMapper.map(task, CreatedTaskDto.class))
                .collect(Collectors.toList());
    }

    private UserTask getByUserIdAndTaskId(int userId, int taskId) {

        UserTask userTask;
        try {
            userTask = userTaskDao.getByUsedAndTask(userId, taskId);
        } catch (NoResultException exception) {
            throw new EntityNotFoundException("User is not found with id = " + userId + " or task with id = " + taskId);
        }

        return userTask;
    }

    private User getById(int id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new EntityNotFoundException("User is not found with id = " + id);
        }
        return user;
    }

    private Task getTaskById(int id) {
        Task task = taskDao.getById(id);
        if (task == null) {
            throw new EntityNotFoundException("Task is not found with id = " + id);
        }
        return task;
    }

    public int getByApiKey(String apiKey) {
        User user;
        try {
            user = userDao.getByApiKey(encode(apiKey));
        } catch (NoResultException | EmptyResultDataAccessException exception) {
            throw new BadCredentialsException("User credentials are incorrect");
        }
        return user.getId();
    }

    public String encode(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        str = encoder.encodeBuffer(str.getBytes());
        return str;
    }


    public String decode(String str) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            str = new String(decoder.decodeBuffer(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}