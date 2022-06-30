package com.evertix.tutorshipservice.config;

import com.evertix.tutorshipservice.entities.Course;
import com.evertix.tutorshipservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader {
    private CourseRepository courseRepository;

    @Autowired
    public DataLoader(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        this.LoadData();
    }

    private void LoadData(){
        addCourses();
    }

    void addCourses(){
        List<Course> courseList = new ArrayList<>();

        //Course 1
        Course course1 = new Course("Spanish", "Spanish");
        Course course2 = new Course("History", "History");
        Course course3 = new Course("Arithmetics", "Arithmetics");
        Course course4 = new Course("Geometry", "Geometry");
        Course course5 = new Course("Geography", "Geography");
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
        courseList.add(course5);
        this.courseRepository.saveAll(courseList);


    }
}
