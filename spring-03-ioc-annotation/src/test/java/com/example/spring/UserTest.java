package com.example.spring;

import com.example.spring.controller.UserController;
import com.example.spring.pojo.JdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    @Test
    public void testUser() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean.xml");
        User user = application.getBean("user", User.class);
        System.out.println("user = " + user);
    }

    @Test
    public void testAuto() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean.xml");
        User user = new User("张三", "学生");
        UserController userController = application.getBean("userController", UserController.class);
        userController.addUser(user);
    }

    @Test
    public void testPropertySource() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean.xml");
        JdbcConfig jdbcConfig = application.getBean("jdbcConfig", JdbcConfig.class);
        System.out.println("jdbcConfig = " + jdbcConfig);
    }

    @Test
    public void testComponentScan() {
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(CustomApplication.class);
        UserController userController = application.getBean("userController", UserController.class);
        User user = new User("张三", "学生");
        userController.addUser(user);
    }

}
