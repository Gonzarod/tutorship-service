package com.evertix.sessionservice.service;

import com.evertix.sessionservice.dto.SessionSaveResource;
import com.evertix.sessionservice.entities.Session;
import com.evertix.sessionservice.entities.SessionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SessionService {
    List<Session> getAllSessions();
    Page<Session> getAllSessionsPage(Pageable pageable);
    Session createSession(Long courseId, Long studentId, SessionSaveResource session);
    SessionDetail applySession(Long teacherId, Long sessionId);

    SessionDetail acceptTeacher(Long teacherId, Long sessionId);
    /*
    Page<Session> getAllSessionsByStudentId(Long studentId, Pageable pageable);
    Page<Session> getAllSessionsByCourseName(String courseName, Pageable pageable);
    Page<Session> getAllSessionsByStatus(String status, Pageable pageable);

    Session updateSession(Long courseId, Long studentId, Long sessionId, Session sessionDetails);
    ResponseEntity<?> deleteSession(Long courseId, Long studentId, Long sessionId);

     */
}
