package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService extends Service<User> {
    User save(User newUser);

    void delete(int id);

    User update(User updateUser, int id);
}
