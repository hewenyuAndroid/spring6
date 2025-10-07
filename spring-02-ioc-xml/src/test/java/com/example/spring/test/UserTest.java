package com.example.spring.test;

import com.example.spring.User;
import com.example.spring.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    @Test
    public void testGetUserObjUseClass() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        User bean = applicationContext.getBean(User.class);
        bean.run();
    }

    @Test
    public void testGetUserDaoObjUserClass() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 使用接口类型获取bean对象
        UserDao bean = applicationContext.getBean(UserDao.class);
        bean.run();
    }


}
