package com.evertix.tutorshipservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "courses_teacher")
@NoArgsConstructor
@Getter
@Setter
public class CourseTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long teacherId;

    @ManyToOne
    Course course;
}
