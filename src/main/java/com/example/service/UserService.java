package com.example.service;

import com.example.dto.task.CreatedTaskDto;
import com.example.dto.task.GetTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.task.TaskDto;
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

    MainTaskUserDto takePartInTask(int userId, int taskId, UserTaskDto userTaskDto);

    int getByApiKey(String apiKey);

    String encode(String str);

    String decode(String str);

    MainUserTaskDto approveUserForTask(int userId, int taskId, boolean approved, ApiKeyDto apiKeyDto);

    List<CreatedTaskDto> getAllCreatedTasks(ApiKeyDto apiKeyDto);

    List<GetTaskDto> getDoneTasks(ApiKeyDto apiKeyDto);

}
