package com.example.service.impl;

import com.example.dao.TaskDao;
import com.example.dto.MainTaskDto;
import com.example.dto.TaskDto;
import com.example.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        return null;
    }

    @Override
    public MainTaskDto getTaskById(long id) {
        return null;
    }

    @Override
    public List<MainTaskDto> getAllTasks() {
        return null;
    }

    @Override
    public void deleteTask(long id) {

    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, long id) {
        return null;
    }

}
