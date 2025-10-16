package com.example.spring.controller;

import com.example.spring.User;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 用户层控制器
 */
@Controller
public class UserController {

    /**
     * 使用Setter注入 userService
     */
    @Autowired
    private UserService userService;

    public void addUser(User user) {
        userService.addUser(user);
    }

}
