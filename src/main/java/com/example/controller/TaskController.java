package com.example.controller;

import com.example.dto.MainTaskDto;
import com.example.dto.TaskDto;
import com.example.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/task/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MainTaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public MainTaskDto updateTask(@RequestBody TaskDto taskDto, @PathVariable long id) {
        return taskService.updateTask(taskDto, id);
    }

    @GetMapping("{id}")
    public MainTaskDto getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("tasks")
    public List<MainTaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

}
