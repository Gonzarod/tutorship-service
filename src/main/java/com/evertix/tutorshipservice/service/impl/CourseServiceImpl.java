package com.evertix.tutorshipservice.service.impl;

import com.evertix.tutorshipservice.client.UserClient;
import com.evertix.tutorshipservice.entities.Course;
import com.evertix.tutorshipservice.entities.CourseTeacher;
import com.evertix.tutorshipservice.model.User;
import com.evertix.tutorshipservice.repository.CourseRepository;
import com.evertix.tutorshipservice.repository.CourseTeacherRepository;
import com.evertix.tutorshipservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseTeacherRepository courseTeacherRepository;

    @Autowired
    UserClient userClient;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByTeacherId(Long teacherId) {
        List<CourseTeacher> courseTeacherList = this.courseTeacherRepository.getCourseTeacherByTeacherId(teacherId);
        if (courseTeacherList.isEmpty()) {
            return Collections.emptyList();
        } else {
            return courseTeacherList.stream().map(CourseTeacher::getCourse).collect(Collectors.toList());
        }

    }

    @Override
    public List<Course> associateCourseToTeacher(Long teacherId, Long courseId) {
        Course course = this.courseRepository.findById(courseId).orElse(null);
        User user = this.userClient.getUserById(teacherId);
        if (course != null && user != null) {
            CourseTeacher courseTeacher = new CourseTeacher();
            courseTeacher.setCourse(course);
            courseTeacher.setTeacherId(user.getId());
            this.courseTeacherRepository.saveAndFlush(courseTeacher);
            List<CourseTeacher> courseTeacherList = this.courseTeacherRepository.getCourseTeacherByTeacherId(user.getId());
            return courseTeacherList.stream().map(CourseTeacher::getCourse).collect(Collectors.toList());
        } else  {
            return null;
        }

    }
}
