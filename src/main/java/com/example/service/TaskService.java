package com.example.service;

import com.example.dto.apiKey.ApiKeyDto;
import com.example.dto.pagination.PaginationDto;
import com.example.dto.task.MainTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.task.TaskDto;

import java.util.List;

public interface TaskService {

    MainTaskDto createTask(TaskDto taskDto, int userId);

    MainTaskDto getTaskById(int id);

    PaginationDto<MainTaskDto> getAllTasks(int pageNumber);

    PaginationDto<MainTaskDto> getAllTasksSorted(int pageNumber, String priority, String category, String order);

    void deleteTask(int id, ApiKeyDto apiKeyDto);

    MainTaskDto updateTask(TaskDto taskDto, int id);

    List<MainUserTaskDto> getAllUsersByTaskId(int id);

}
