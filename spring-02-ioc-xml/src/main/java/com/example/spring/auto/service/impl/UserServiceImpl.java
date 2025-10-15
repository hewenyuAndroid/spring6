package com.example.spring.auto.service.impl;

import com.example.spring.User;
import com.example.spring.auto.dao.UserDao;
import com.example.spring.auto.service.UserService;
import lombok.Data;

@Data
public class UserServiceImpl implements UserService {

    private UserDao userDao;


    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

}
