package com.example.spring.auto.dao.impl;

import com.example.spring.User;
import com.example.spring.auto.dao.UserDao;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) {
        System.out.println("UserDao addUser: " + user);
    }

}
