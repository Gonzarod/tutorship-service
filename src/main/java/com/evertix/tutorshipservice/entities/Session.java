package com.evertix.tutorshipservice.entities;

import com.evertix.tutorshipservice.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
@Data
@JsonIgnoreProperties(value = {"studentId","courseId"},allowSetters = true)
public class Session {

    public Session(LocalDateTime start_at, LocalDateTime end_at, String status, String topic, String link, Long studentId, Long courseId) {
        this.start_at=start_at;
        this.end_at=end_at;
        this.status=status;
        this.topic=topic;
        this.link=link;
        this.studentId=studentId;
        this.courseId=courseId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime start_at;

    @Column(nullable = false, updatable = false)
    private LocalDateTime end_at;

    @NotNull(message = "Status cannot be null")
    @NotBlank(message = "Status cannot be blank")
    @Size(max = 20)
    private String status;

    @NotNull(message = "Topic cannot be null")
    @NotBlank(message = "Topic cannot be blank")
    @Size(max = 150)
    private String topic;

    @NotNull(message = "Link cannot be null")
    @NotBlank(message = "Link cannot be blank")
    @Size(max = 150)
    private String link;

    @Column(name="student_id")
    private Long studentId;

    @Column(name="course_id")
    private Long courseId;

    @Transient
    private User studentModel;

    @Transient
    private Course courseModel;


    public Session() {

    }
}
