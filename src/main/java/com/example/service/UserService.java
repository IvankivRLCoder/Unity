package com.example.service;

import com.example.dto.MainUserDto;
import com.example.dto.UserDto;

import java.util.List;

public interface UserService {

    MainUserDto createUser(UserDto userDto);

    MainUserDto getUserById(long id);

    List<MainUserDto> getAllUsers();

    void deleteUser(long id);

    MainUserDto updateUser(UserDto userDto, long id);

}
