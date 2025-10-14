package com.example.spring.test;

import com.example.spring.jdbc.MysqlConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OuterPropertyTest {

    @Test
    public void testLoadMysqlConfig() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-di-outer-property.xml");
        MysqlConfig mysqlConfig = application.getBean("mysqlConfig", MysqlConfig.class);
        System.out.println("mysqlConfig = " + mysqlConfig);
    }

}
