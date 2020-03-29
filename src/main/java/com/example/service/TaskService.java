package com.example.service;

import com.example.dto.MainTaskDto;
import com.example.dto.TaskDto;

import java.util.List;

public interface TaskService {

    MainTaskDto createTask(TaskDto taskDto);

    MainTaskDto getTaskById(int id);

    List<MainTaskDto> getAllTasks();

    void deleteTask(int id);

    MainTaskDto updateTask(TaskDto taskDto, int id);

}
