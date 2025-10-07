package com.example.spring.dao.impl;

import com.example.spring.dao.UserDao;

public class PersonDaoImpl implements UserDao {
    @Override
    public void run() {
        System.out.println("PersonDaoImpl.run...");
    }
}
