package com.evertix.sessionservice.service.impl;

import com.evertix.sessionservice.entities.Session;
import com.evertix.sessionservice.model.Course;
import com.evertix.sessionservice.model.User;
import com.evertix.sessionservice.repository.SessionRepository;
import com.evertix.sessionservice.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll().stream().map(session -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+session.getStudentId(),User.class);
            User student=restTemplate.getForObject("http://tutofast-user-service.eastus.azurecontainer.io:8085/api/users/"+session.getStudentId(),User.class);
            Course course=restTemplate.getForObject("http://tutofast-user-service.eastus.azurecontainer.io:8085/api/courses/"+session.getCourseId(),Course.class);
            session.setStudentModel(student);
            session.setCourseModel(course);
            return session;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<Session> getAllSessionsPage(Pageable pageable) {
        Page<Session> page=sessionRepository.findAll(pageable);
        List<Session> result=page.getContent().stream().map(session -> {
            User student=restTemplate.getForObject("http://tutofast-user-service.eastus.azurecontainer.io:8085/api/users/"+session.getStudentId(),User.class);
            Course course=restTemplate.getForObject("http://tutofast-user-service.eastus.azurecontainer.io:8085/api/courses/"+session.getCourseId(),Course.class);
            session.setStudentModel(student);
            session.setCourseModel(course);
            return session;
        }).collect(Collectors.toList());
        return new PageImpl<>(result,pageable, page.getTotalElements());
    }



  /*
    @Override
    public Page<Session> getAllSessionsByStudentId(Long studentId, Pageable pageable) {
        return sessionRepository.getAllByStudentId(studentId, pageable);
    }


    @Override
    public Page<Session> getAllSessionsByCourseName(String courseName, Pageable pageable) {
        return sessionRepository.getAllByCourseName(courseName, pageable);
    }

    @Override
    public Page<Session> getAllSessionsByStatus(String status, Pageable pageable) {
        return sessionRepository.getAllByStatus(status, pageable);
    }

    @Override
    public Session createSession(Long courseId, Long studentId, Session session) {
        return courseRepository.findById(courseId).map(course -> {
            return userRepository.findById(studentId).map(user -> {
                session.setStudent(user);
                session.setCourse(course);
                return sessionRepository.save(session);
            }).orElseThrow(()-> new ResourceNotFoundException("Student with Id: "+studentId+" not found"));
        }).orElseThrow(()-> new ResourceNotFoundException("Course with Id: "+courseId+" not found"));
    }

    @Override
    public Session updateSession(Long courseId, Long studentId, Long sessionId, Session sessionDetails) {
        return courseRepository.findById(courseId).map(course -> {
            return userRepository.findById(studentId).map(user -> {
                return sessionRepository.findById(sessionId).map(session -> {
                    session.setStudent(user);
                    session.setCourse(course);
                    session.setStatus(sessionDetails.getStatus());
                    session.setTopic(sessionDetails.getTopic());
                    session.setLink(sessionDetails.getLink());
                    return sessionRepository.save(session);
                }).orElseThrow(()-> new ResourceNotFoundException("Session with Id: "+sessionId+" not found"));
            }).orElseThrow(()-> new ResourceNotFoundException("Student with Id: "+studentId+" not found"));
        }).orElseThrow(()-> new ResourceNotFoundException("Course with Id: "+courseId+" not found"));
    }

    @Override
    public ResponseEntity<?> deleteSession(Long courseId, Long studentId, Long sessionId) {
        return courseRepository.findById(courseId).map(course -> {
            return userRepository.findById(studentId).map(user -> {
                return sessionRepository.findById(sessionId).map(session -> {
                    sessionRepository.delete(session);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Session with Id: "+sessionId+" not found"));
            }).orElseThrow(()-> new ResourceNotFoundException("Student with Id: "+studentId+" not found"));
        }).orElseThrow(()-> new ResourceNotFoundException("Course with Id: "+courseId+" not found"));
    }

     */
}
