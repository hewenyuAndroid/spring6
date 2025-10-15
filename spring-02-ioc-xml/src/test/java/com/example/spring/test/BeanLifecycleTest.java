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

    @Test
    public void testScope() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-lifecycle.xml");
        // 获取单例对象
        Stock stockSingleton1 = application.getBean("stockSingleton", Stock.class);
        Stock stockSingleton2 = application.getBean("stockSingleton", Stock.class);

        // 获取原型对象
        Stock stockPrototype1 = application.getBean("stockPrototype", Stock.class);
        Stock stockPrototype2 = application.getBean("stockPrototype", Stock.class);

        System.out.println("testScope: stockSingleton1=" + stockSingleton1.hashCode() + ", stockSingleton2=" + stockSingleton2.hashCode() +
                ", stockPrototype1=" + stockPrototype1.hashCode() + ", stockPrototype2=" + stockPrototype2.hashCode());

    }

    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-lifecycle.xml");
        Stock stockFactoryBean = application.getBean("stockFactoryBean", Stock.class);
        System.out.println("stockFactoryBean: " + stockFactoryBean);
    }


}
