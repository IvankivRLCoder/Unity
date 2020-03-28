package com.example.dao;

import com.example.model.User;

public interface UserDao extends MainDao<User> {

    User getById(long id);

    User update(User user);

    void delete(User user);

    User getByEmail(String email);

}
