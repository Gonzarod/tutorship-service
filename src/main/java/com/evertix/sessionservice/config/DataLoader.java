package com.evertix.sessionservice.config;

import com.evertix.sessionservice.entities.Session;
import com.evertix.sessionservice.entities.SessionDetail;
import com.evertix.sessionservice.model.Course;
import com.evertix.sessionservice.model.User;
import com.evertix.sessionservice.repository.SessionDetailRepository;
import com.evertix.sessionservice.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader {

    private SessionRepository sessionRepository;
    private SessionDetailRepository sessionDetailRepository;
    private RestTemplate restTemplate;

    @Autowired
    public DataLoader(SessionRepository sessionRepository, SessionDetailRepository sessionDetailRepository,RestTemplate restTemplate) {
        this.sessionRepository=sessionRepository;
        this.sessionDetailRepository=sessionDetailRepository;
        this.restTemplate=restTemplate;
        LoadData();
    }

    private void LoadData() {

        User userStudent = restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/username/jesus.student", User.class);
        Course course = restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/courses/name/Geography",Course.class);
        User userTeacher = restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/username/albert.teacher", User.class);

        LocalDateTime start_at = LocalDateTime.of(2020,Month.OCTOBER,5,0,0,0);
        LocalDateTime end_at = LocalDateTime.of(2020, Month.OCTOBER,5,11,0,0);

        Session session = new Session(Date.from(start_at.toInstant(ZoneOffset.UTC)),
                                      Date.from(end_at.toInstant(ZoneOffset.UTC)),
                                "Pendiente","Integrales","www.skype.com/a12",userStudent.getId(),course.getId());

        this.sessionRepository.save(session);

        
        SessionDetail sessionDetail = new SessionDetail("Aceptación Pendiente",userTeacher.getId());
        sessionDetail.setSession(session);
        this.sessionDetailRepository.save(sessionDetail);

    }
}