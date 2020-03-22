package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.example.view.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping(value = "/")
    public UserViewModel addUser (UserViewModel userViewModel) {
        return new UserViewModel(userService.addUser(new User(userViewModel)));
    }

    @GetMapping
    public List<UserViewModel> getUsers() {
        return userService.getUsers().stream().map(UserViewModel::new).collect(Collectors.toList());
    }
}
