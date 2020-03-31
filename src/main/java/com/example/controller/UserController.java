package com.example.controller;

import com.example.dto.user.MainTaskUserDto;
import com.example.dto.user.MainUserDto;
import com.example.dto.user.UserDto;
import com.example.dto.usertask.UserTaskDto;
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

    @PostMapping("/")
    @ApiOperation(value = "Add new user")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New user created", response = MainUserDto.class),
            @ApiResponse(code = 400, message = "Validation error", response = ApiError.class)
    })
    public MainUserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully deleted"),
            @ApiResponse(code = 404, message = "Non-existing user id", response = ApiError.class)
    })
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
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

    @GetMapping("/{id}/tasks")
    @ApiOperation(value = "View a list of all tasks by user id")
    @ApiResponse(code = 200, message = "List of all users", response = MainTaskUserDto.class)
    public List<MainTaskUserDto> getAllTasksByUserId(@PathVariable int id) {
        return userService.getAllTasksByUserId(id);
    }

    @PostMapping("/{userId}/tasks/{taskId}")
    @ApiOperation(value = "Take part in task")
    @ApiResponse(code = 200, message = "List of all users", response = UserTaskDto.class)
    public UserTaskDto takePartInTask(@PathVariable int userId, @PathVariable int taskId, @Valid @RequestBody UserTaskDto userTaskDto) {
        return userService.takePartInTask(userId, taskId, userTaskDto);
    }

}