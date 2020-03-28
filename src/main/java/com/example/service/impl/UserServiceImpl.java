package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dao.UserTaskDao;
import com.example.dto.MainUserDto;
import com.example.dto.UserDto;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public MainUserDto createUser(UserDto userDto) {
        return null;
    }

    @Override
    public MainUserDto getUserById(long id) {
        return null;
    }

    @Override
    public List<MainUserDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public MainUserDto updateUser(UserDto userDto, long id) {
        return null;
    }

}
