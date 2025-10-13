package com.example.spring.collection_di;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String id;

    private String name;

    private List<Course> courseList;

    private List<String> scoreList;

    private Map<String, Teacher> tMap;

}
