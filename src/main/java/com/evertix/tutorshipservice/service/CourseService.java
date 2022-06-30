package com.evertix.tutorshipservice.service;

import com.evertix.tutorshipservice.entities.Course;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {
    List<Course> getCoursesByTeacherId(Long teacherId);

    List<Course> getAllCourses();

    List<Course> associateCourseToTeacher(Long teacherId, Long courseId);
}
