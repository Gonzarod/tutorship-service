package com.evertix.tutorshipservice.controller;

import com.evertix.tutorshipservice.entities.Course;
import com.evertix.tutorshipservice.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Course", description = "API")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/courses")
    List<Course> getCourses() {
        return this.courseService.getAllCourses();
    }

    @GetMapping("/teacher/{teacherId}/courses")
    List<Course> getCoursesByTeacherId(@PathVariable Long teacherId) {
        return this.courseService.getCoursesByTeacherId(teacherId);
    }

    @PostMapping("/teacher/{teacherId}/course/{courseId}")
    List<Course> associateCourseToTeacher(@PathVariable Long teacherId, @PathVariable Long courseId) {
        return this.courseService.associateCourseToTeacher(teacherId, courseId);
    }

}
