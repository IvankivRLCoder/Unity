package com.example.service.impl;

import com.example.dao.TaskDao;
import com.example.dto.task.MainTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.task.TaskDto;
import com.example.model.Task;
import com.example.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    private final ModelMapper modelMapper;

    @Override
    public MainTaskDto createTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        return modelMapper.map(taskDao.save(task), MainTaskDto.class);
    }

    @Override
    public MainTaskDto getTaskById(int id) {
        return modelMapper.map(getById(id), MainTaskDto.class);
    }

    @Override
    public List<MainTaskDto> getAllTasks() {
        return taskDao.getAll().stream().map(task -> modelMapper.map(task, MainTaskDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteTask(int id) {
        taskDao.delete(getById(id));
    }

    @Override
    public MainTaskDto updateTask(TaskDto taskDto, int id) {
        Task task = getById(id);
        Task newTask = modelMapper.map(taskDto, Task.class);

        task.setCategory(newTask.getCategory());
        task.setCreationDate(newTask.getCreationDate());
        task.setDescription(newTask.getDescription());
        task.setName(newTask.getName());
        task.setPriority(newTask.getPriority());
        task.setPossibleNumberOfParticipants(newTask.getPossibleNumberOfParticipants());
        task.setStatus(newTask.getStatus());
        task.setTitle(newTask.getTitle());

        return modelMapper.map(taskDao.update(task), MainTaskDto.class);
    }

    @Override
    public List<MainUserTaskDto> getAllUsersByTaskId(int id) {
        return taskDao.getById(id).getUserTasks().stream()
                .map(userTask -> modelMapper.map(userTask, MainUserTaskDto.class))
                .collect(Collectors.toList());
    }

    private Task getById(int id) {
        Task task = taskDao.getById(id);
        if (task == null) {
            throw new EntityNotFoundException("Task is not found with id = " + id);
        }
        return task;
    }

}
