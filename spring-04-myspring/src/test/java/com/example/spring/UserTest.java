package com.example.spring;

import com.example.spring.bean.impl.AnnotationApplicationContext;
import com.example.spring.service.UserService;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testIoc() {
        AnnotationApplicationContext application = new AnnotationApplicationContext("com.example.spring");
        Object bean = application.getBean(UserService.class);
        System.out.println("bean:" + bean);
    }

}
