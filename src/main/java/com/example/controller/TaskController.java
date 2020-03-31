package com.example.controller;

import com.example.dto.task.MainTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.task.TaskDto;
import com.example.dto.user.MainUserDto;
import com.example.error.ApiError;
import com.example.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/Unity/tasks")
@RequiredArgsConstructor
@Api(tags = "Task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new Task")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New task created", response = TaskDto.class),
            @ApiResponse(code = 400, message = "Validation error", response = ApiError.class)
    })
    public MainTaskDto createTask(@Valid @RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete task by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task successfully deleted"),
            @ApiResponse(code = 404, message = "Non-existing task id", response = ApiError.class)
    })
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update task by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task successfully updated", response = TaskDto.class),
            @ApiResponse(code = 400, message = "Validation error", response = ApiError.class),
            @ApiResponse(code = 404, message = "Non-existing task id", response = ApiError.class)
    })
    public MainTaskDto updateTask(@Valid @RequestBody TaskDto taskDto, @PathVariable int id) {
        return taskService.updateTask(taskDto, id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get task by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task found", response = MainTaskDto.class),
            @ApiResponse(code = 404, message = "Non-existing task id", response = ApiError.class)
    })
    public MainTaskDto getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View list of all the tasks")
    @ApiResponse(code = 200, message = "List of all tasks", response = MainTaskDto.class)
    public List<MainTaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "View a list of all participants of the task by id")
    @ApiResponse(code = 200, message = "List of all users", response = MainUserDto.class)
    public List<MainUserTaskDto> getAllUsersByTaskId(@PathVariable int id) {
        return taskService.getAllUsersByTaskId(id);
    }

}