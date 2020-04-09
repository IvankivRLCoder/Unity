package com.example.controller;

import com.example.dto.task.CreatedTaskDto;
import com.example.dto.task.GetTaskDto;
import com.example.dto.task.MainTaskDto;
import com.example.dto.task.TaskDto;
import com.example.dto.user.ApiKeyDto;
import com.example.dto.user.MainTaskUserDto;
import com.example.dto.user.MainUserDto;
import com.example.dto.user.UserDto;
import com.example.error.ApiError;
import com.example.service.UserService;
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
@RequestMapping(path = "/Unity/users")
@RequiredArgsConstructor
@Api(tags = "User")
public class UserController {

    private final UserService userService;

    @PostMapping("/{id}/tasks")
    @ApiOperation(value = "All created tasks")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created tasks", response = TaskDto.class),
            @ApiResponse(code = 400, message = "Validation error", response = ApiError.class)
    })
    public List<CreatedTaskDto> getAllCreatedTasks(@PathVariable int id, @Valid ApiKeyDto apiKeyDto) {
        return userService.getAllCreatedTasks(apiKeyDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully deleted"),
            @ApiResponse(code = 404, message = "Non-existing user id", response = ApiError.class)
    })
    public void deleteUser(@PathVariable int id, @RequestBody ApiKeyDto apiKeyDto) {
        userService.deleteUser(id, apiKeyDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user by id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully updated", response = MainUserDto.class),
            @ApiResponse(code = 400, message = "Validation error", response = ApiError.class),
            @ApiResponse(code = 404, message = "Non-existing user id", response = ApiError.class)
    })
    public MainUserDto updateUser(@RequestBody UserDto userDto, @PathVariable int id) {
        return userService.updateUser(userDto, id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found", response = MainUserDto.class),
            @ApiResponse(code = 404, message = "Non-existing user id", response = ApiError.class)
    })
    public MainUserDto getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/")
    @ApiOperation(value = "View a list of all users")
    @ApiResponse(code = 200, message = "List of all users", response = MainUserDto.class)
    public List<MainUserDto> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping("/tasks/done")
    @ApiOperation(value = "View a list of done tasks by user id")
    @ApiResponse(code = 200, message = "List of all users", response = MainUserDto.class)
    public List<GetTaskDto> getDoneTasks(@RequestBody ApiKeyDto apiKeyDto) {
        return userService.getDoneTasks(apiKeyDto);
    }



}