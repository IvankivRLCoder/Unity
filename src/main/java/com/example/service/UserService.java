package com.example.service;

import com.example.dto.user.MainUserDto;
import com.example.dto.user.RegisteredUserDto;
import com.example.dto.user.UserDto;

import java.util.List;

public interface UserService {

    RegisteredUserDto createUser(UserDto userDto);

    MainUserDto getUserById(int id);

    List<MainUserDto> getAllUsers();

    void deleteUser(int id);

    MainUserDto updateUser(UserDto userDto, int id);

}
