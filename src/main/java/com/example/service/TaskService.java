package com.example.service;

import com.example.dto.task.MainTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.task.TaskDto;
import com.example.dto.user.ApiKeyDto;

import java.util.List;

public interface TaskService {

    MainTaskDto createTask(TaskDto taskDto, int userId);

    MainTaskDto getTaskById(int id, ApiKeyDto apiKeyDto);

    List<MainTaskDto> getAllTasks(ApiKeyDto apiKeyDto);

    void deleteTask(int id, ApiKeyDto apiKeyDto);

    MainTaskDto updateTask(TaskDto taskDto, int id);

    List<MainUserTaskDto> getAllUsersByTaskId(int id, ApiKeyDto apiKeyDto);

}
