package com.evertix.tutorshipservice.repository;

import com.evertix.tutorshipservice.entities.Course;
import com.evertix.tutorshipservice.entities.CourseTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTeacherRepository extends JpaRepository<CourseTeacher,Long> {
    List<CourseTeacher> getCourseTeacherByTeacherId(Long teacherId);
}
