package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.example.view.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public UserViewModel registration(UserViewModel userViewModel) {
        return new UserViewModel(userService.save(new User(userViewModel)));
    }

    @PostMapping(value = "/login")
    public String login(UserViewModel userViewModel) {
        UserViewModel neededUser = userService.getAll().stream().filter(user -> {
            return user.getEmail().equals(userViewModel.getEmail()) &&
                    user.getPassword().equals(userViewModel.getPassword());
        }).map(UserViewModel::new).findFirst().orElse(null);
        if (neededUser == null) {
            return "Bad email or password. Try again.";
        }
        return "OK";
    }
}
