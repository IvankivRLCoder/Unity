package com.example.service.impl;

import com.example.dao.TaskDao;
import com.example.dao.UserDao;
import com.example.dao.UserTaskDao;
import com.example.dto.user.ApiKeyDto;
import com.example.dto.user.MainTaskUserDto;
import com.example.dto.user.MainUserDto;
import com.example.dto.user.UserDto;
import com.example.dto.usertask.UserTaskDto;
import com.example.error.BadCredentialsException;
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
import java.util.List;
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
    public UserTaskDto takePartInTask(int userId, int taskId, UserTaskDto userTaskDto) {
        getByApiKey(userTaskDto.getApiKey());
        UserTask userTask = modelMapper.map(userTaskDto, UserTask.class);
        userTask.setUser(getById(userId));
        userTask.setTask(getTaskById(taskId));
        return modelMapper.map(userTaskDao.save(userTask), UserTaskDto.class);
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

    public int getByApiKey(String apiKey){
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