package com.example.spring.test;

import com.example.spring.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmpTest {

    @Test
    public void testEmp(){
        // 外部bean引入方式
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-di.xml");
        Emp emp = application.getBean("emp1", Emp.class);
        System.out.println(emp);
    }

    @Test
    public void testEmp2(){
        // 内部bean引入方式
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-di.xml");
        Emp emp = application.getBean("emp2", Emp.class);
        System.out.println(emp);
    }

    @Test
    public void testEmp3(){
        // 内部bean引入方式
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-di.xml");
        Emp emp = application.getBean("emp3", Emp.class);
        System.out.println(emp);
    }

}
