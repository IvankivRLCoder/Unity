package com.example.service.impl;

import com.example.dao.TaskDao;
import com.example.dao.UserDao;
import com.example.dao.UserTaskDao;
import com.example.dto.user.MainTaskUserDto;
import com.example.dto.user.MainUserDto;
import com.example.dto.user.UserDto;
import com.example.dto.usertask.UserTaskDto;
import com.example.error.OverflowingTaskException;
import com.example.error.TaskIsNotActiveException;
import com.example.error.UserIsCreatorException;
import com.example.error.UserIsParticipantAlreadyException;
import com.example.model.Task;
import com.example.model.User;
import com.example.model.UserTask;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
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

    //TODO FILE EXTENSIONS
    @Override
    public MainUserDto createUser(UserDto userDto) {
        String photo = userDto.getPhoto();
        String fileName = UUID.randomUUID().toString();
        File photoFile = new File("Unity/src/main/resources/static/image/" + fileName + ".jpg");
        decodeImage(photo, photoFile.getAbsolutePath());
        userDto.setPhoto(fileName);
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userDao.save(user), MainUserDto.class);
    }

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
    public void deleteUser(int id) {
        User user = getById(id);
        userDao.delete(user);
    }

    @Override
    public MainUserDto updateUser(UserDto userDto, int id) {
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
    public UserTaskDto takePartInTask(int userId, int taskId, UserTaskDto userTaskDto) {
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

        return modelMapper.map(userTaskDao.save(userTask), UserTaskDto.class);
    }

    //TODO: errorHandling after realization auth
    @Override
    public UserTaskDto approveUserForTask(int userId, int taskId, boolean approved) {
        UserTask userTask = getByUserIdAndTaskId(userId, taskId);
        userTask.setApproved(approved);
        return modelMapper.map(userTaskDao.update(userTask), UserTaskDto.class);
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

    private void decodeImage(String base64Image, String pathFile) {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.err.println("Image not found" + e);
        } catch (IOException ioe) {
            System.err.println("Exception while reading the Image " + ioe);
        }
    }

}