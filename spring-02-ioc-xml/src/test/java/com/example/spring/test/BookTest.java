package com.example.spring.test;

import com.example.spring.di.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookTest {

    @Test
    public void testGetBookObj() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-di.xml");
//        Book bean = applicationContext.getBean(Book.class);
        Book bean = applicationContext.getBean("book", Book.class);
        System.out.println(bean);
    }

    @Test
    public void testGetBookObjCons() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-di.xml");
        Book bean = applicationContext.getBean("book1", Book.class);
        System.out.println(bean);
    }


}
