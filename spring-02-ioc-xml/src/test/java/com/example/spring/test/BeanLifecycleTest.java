package com.example.spring.test;

import com.example.spring.lifecycle.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycleTest {

    @Test
    public void testAllArgsCons() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-lifecycle.xml");
        Stock stock1 = application.getBean("stock1", Stock.class);
        System.out.println("stock1: " + stock1);
        // 关闭容器，触发bean的销毁操作
        application.close();
    }

    @Test
    public void testNoArgsCons() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-lifecycle.xml");
        Stock stock2 = application.getBean("stock2", Stock.class);
        System.out.println("stock2: " + stock2);
        // 关闭容器，触发bean的销毁操作
        application.close();
    }


}
