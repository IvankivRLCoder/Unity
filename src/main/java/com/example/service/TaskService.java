package com.example.service;

import com.example.dto.MainTaskDto;
import com.example.dto.TaskDto;

import java.util.List;

public interface TaskService {

    MainTaskDto createTask(TaskDto taskDto);

    MainTaskDto getTaskById(long id);

    List<MainTaskDto> getAllTasks();

    void deleteTask(long id);

    MainTaskDto updateTask(TaskDto taskDto, long id);

}
