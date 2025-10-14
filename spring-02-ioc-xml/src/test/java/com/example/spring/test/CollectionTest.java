package com.example.spring.test;

import com.example.spring.collection_di.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CollectionTest {

    @Test
    public void testListDi() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-collection-di.xml");
        Student stud1 = application.getBean("stud1", Student.class);
        System.out.println("stud1: " + stud1);
    }

    @Test
    public void testListDi2() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-collection-di.xml");
        Student stud2 = application.getBean("stud2", Student.class);
        System.out.println("stud2: " + stud2);
    }

    @Test
    public void testListDi3() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-collection-di.xml");
        Student stud3 = application.getBean("stud3", Student.class);
        System.out.println("stud3: " + stud3);
    }

    @Test
    public void testList04() {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("bean-p-namespace-di.xml");
        Student stud1 = application.getBean("stud1", Student.class);
        System.out.println("stud1: " + stud1);
    }

}
