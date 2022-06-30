package com.evertix.tutorshipservice.repository;

import com.evertix.tutorshipservice.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Course getCourseById(Long id);
}
