package com.example.service;

import com.example.dto.MainUserDto;
import com.example.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    MainUserDto getUserById(long id);

    List<MainUserDto> getAllUsers();

    void deleteUser(long id);

    UserDto updateUser(UserDto userDto, long id);

}
