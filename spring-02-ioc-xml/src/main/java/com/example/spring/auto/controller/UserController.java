package com.example.spring.auto.controller;

import com.example.spring.User;
import com.example.spring.auto.service.UserService;
import lombok.Data;

@Data
public class UserController {

    private UserService userService;

    public void addUser(User user) {
        userService.addUser(user);
    }

}
