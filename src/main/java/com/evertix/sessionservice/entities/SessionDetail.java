package com.evertix.sessionservice.entities;

import com.evertix.sessionservice.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "session_details")
@Getter
@Setter
@JsonIgnoreProperties(value = "teacherId",allowSetters = true)
public class SessionDetail extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "State cannot be null")
    @NotBlank(message = "State cannot be blank")
    @Size(max = 80)
    private String state;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Session session;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Transient
    private User teacherModel;

    public SessionDetail(String state, Long teacherId) {
        this.state=state;
        this.teacherId=teacherId;
    }

    public SessionDetail() {

    }
}
