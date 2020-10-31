package com.evertix.sessionservice.service;

import com.evertix.sessionservice.entities.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SessionService {
    List<Session> getAllSessions();
    Page<Session> getAllSessionsPage(Pageable pageable);

    /*
    Page<Session> getAllSessionsByStudentId(Long studentId, Pageable pageable);
    Page<Session> getAllSessionsByCourseName(String courseName, Pageable pageable);
    Page<Session> getAllSessionsByStatus(String status, Pageable pageable);
    Session createSession(Long courseId, Long studentId, Session session);
    Session updateSession(Long courseId, Long studentId, Long sessionId, Session sessionDetails);
    ResponseEntity<?> deleteSession(Long courseId, Long studentId, Long sessionId);

     */
}
