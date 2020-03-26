package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User save(User newUser) {
        return userDao.save(newUser);
    }

    @Override
    public void delete(int id) {
        User user = userDao.getById(id);
        userDao.delete(user);
    }

    @Override
    public User update(User updateUser, int id) {
        User oldUser = userDao.getById(id);
        oldUser.setEmail(updateUser.getEmail());
        oldUser.setName(updateUser.getName());
        oldUser.setPassword(updateUser.getPassword());
        oldUser.setSurname(updateUser.getSurname());
        return userDao.update(oldUser);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
