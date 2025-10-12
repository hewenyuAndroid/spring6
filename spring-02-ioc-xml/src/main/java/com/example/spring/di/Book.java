package com.example.spring.di;

import lombok.Data;

@Data
public class Book {

    public Book() {
        System.out.println("Book constructor 无参构造...");
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        System.out.println("Book constructor 有参构造...");
    }

    private String name;

    private String author;

}
