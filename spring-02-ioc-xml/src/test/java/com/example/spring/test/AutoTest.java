package com.example.spring.test;

import com.example.spring.User;
import com.example.spring.auto.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoTest {

    @Test
    public void testAuto() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-auto.xml");
        User user = application.getBean(User.class);
        UserController userController = application.getBean("userController", UserController.class);
        userController.addUser(user);
    }

}
