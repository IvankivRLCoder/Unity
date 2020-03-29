package com.example.controller;

import com.example.dto.MainUserDto;
import com.example.dto.UserDto;
import com.example.error.ApiError;
import com.example.model.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            @ApiResponse(code = 201, message = "New user created", response = UserDto.class),
            @ApiResponse(code = 400, message = "Validation error", response = ApiError.class)
    })
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully deleted"),
            @ApiResponse(code = 404, message = "Non-existing user id", response = ApiError.class)
    })
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user by id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully updated", response = UserDto.class),
            @ApiResponse(code = 400, message = "Validation error", response = ApiError.class),
            @ApiResponse(code = 404, message = "Non-existing user id", response = ApiError.class)
    })
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable long id) {
        return userService.updateUser(userDto, id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id")
    @ResponseStatus(HttpStatus.FOUND)
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "User found", response = MainUserDto.class),
            @ApiResponse(code = 404, message = "Non-existing user id", response = ApiError.class)
    })
    public MainUserDto getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/")
    @ApiOperation(value = "View a list of all users")
    @ApiResponse(code = 200, message = "List of all users", response = MainUserDto.class)
    public List<MainUserDto> getAllUsers() {
        return userService.getAllUsers();
    }

}