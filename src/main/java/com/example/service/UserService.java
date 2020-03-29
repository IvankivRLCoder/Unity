package com.example.service;

import com.example.dto.MainUserDto;
import com.example.dto.UserDto;

import java.util.List;

public interface UserService {

    MainUserDto createUser(UserDto userDto);

    MainUserDto getUserById(int id);

    List<MainUserDto> getAllUsers();

    void deleteUser(int id);

    MainUserDto updateUser(UserDto userDto, int id);

}
