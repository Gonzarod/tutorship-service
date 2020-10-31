package com.evertix.sessionservice.model;

import lombok.Data;

@Data
public class Course {

    public Course() { }

    public Course(String name, String description){
        this.name = name;
        this.description = description;
    }

    private Long id;


    private String name;

    private String description;

}
