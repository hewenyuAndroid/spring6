package com.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    @Test
    public void testUserObject() {
        // 加载 spring 的配置文件，创建对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 获取 bean.xml 中配置的 User 对象
        User user = (User) context.getBean("user");
        // 执行 user对象的方法
        user.add();
    }

}
