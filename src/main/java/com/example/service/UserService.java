package com.example.service;

import com.example.dto.user.MainTaskUserDto;
import com.example.dto.user.MainUserDto;
import com.example.dto.user.UserDto;
import com.example.dto.usertask.UserTaskDto;

import java.util.List;

public interface UserService {

    MainUserDto createUser(UserDto userDto);

    MainUserDto getUserById(int id);

    List<MainUserDto> getAllUsers();

    void deleteUser(int id);

    MainUserDto updateUser(UserDto userDto, int id);

    List<MainTaskUserDto> getAllTasksByUserId(int id);

    UserTaskDto takePartInTask(int userId, int taskId, UserTaskDto userTaskDto);
}
