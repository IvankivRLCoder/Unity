package com.example.service;

import com.example.dto.task.MainTaskDto;
import com.example.dto.task.OnlyTaskDto;
import com.example.dto.task.TaskDto;

import java.util.List;

public interface TaskService {

    OnlyTaskDto createTask(TaskDto taskDto);

    MainTaskDto getTaskById(int id);

    List<MainTaskDto> getAllTasks();

    void deleteTask(int id);

    MainTaskDto updateTask(TaskDto taskDto, int id);

}
