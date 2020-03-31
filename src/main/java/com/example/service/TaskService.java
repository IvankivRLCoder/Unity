package com.example.service;

import com.example.dto.task.MainTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.task.TaskDto;

import java.util.List;

public interface TaskService {

    MainTaskDto createTask(TaskDto taskDto);

    MainTaskDto getTaskById(int id);

    List<MainTaskDto> getAllTasks();

    void deleteTask(int id);

    MainTaskDto updateTask(TaskDto taskDto, int id);

    List<MainUserTaskDto> getAllUsersByTaskId(int id);

}
