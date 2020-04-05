package com.example.service;

import com.example.dto.user.ApiKeyDto;
import com.example.dto.user.MainTaskUserDto;
import com.example.dto.user.MainUserDto;
import com.example.dto.user.UserDto;
import com.example.dto.usertask.UserTaskDto;

import java.util.List;

public interface UserService {

    MainUserDto getUserById(int id);

    List<MainUserDto> getAllUsers();

    void deleteUser(int id, ApiKeyDto apiKeyDto);

    MainUserDto updateUser(UserDto userDto, int id);

    List<MainTaskUserDto> getAllTasksByUserId(int id);

    UserTaskDto takePartInTask(int userId, int taskId, UserTaskDto userTaskDto);

    int getByApiKey(String apiKey);

    String encode(String str);

    String decode(String str);

}
