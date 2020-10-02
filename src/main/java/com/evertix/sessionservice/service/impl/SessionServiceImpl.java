package com.evertix.sessionservice.service.impl;

import com.evertix.sessionservice.entities.Session;
import com.evertix.sessionservice.repository.SessionRepository;
import com.evertix.sessionservice.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Page<Session> getAllSessions(Pageable pageable) {
        return this.sessionRepository.findAll(pageable);
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
