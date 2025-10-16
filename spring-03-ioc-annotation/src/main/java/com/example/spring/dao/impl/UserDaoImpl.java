package com.example.spring.dao.impl;

import com.example.spring.User;
import com.example.spring.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) {
        System.out.println("UserDao: addUser(), user=" + user);

    }

}
