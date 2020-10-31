package com.evertix.sessionservice.service.impl;

import com.evertix.sessionservice.entities.SessionDetail;
import com.evertix.sessionservice.model.User;
import com.evertix.sessionservice.repository.SessionDetailRepository;
import com.evertix.sessionservice.service.SessionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionDetailServiceImpl implements SessionDetailService {
    @Autowired
    private SessionDetailRepository sessionDetailRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<SessionDetail> getAllSessionDetails() {
        return sessionDetailRepository.findAll().stream().map(sessionDetail -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+session.getStudentId(),User.class);
            User teacher=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+sessionDetail.getTeacherId(),User.class);

            sessionDetail.setTeacherModel(teacher);
            return sessionDetail;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<SessionDetail> getAllSessionDetailsPage(Pageable pageable) {
        Page<SessionDetail> page=sessionDetailRepository.findAll(pageable);
        List<SessionDetail> result=page.getContent().stream().map(sessionDetail -> {
            User teacher=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+sessionDetail.getTeacherId(),User.class);

            sessionDetail.setTeacherModel(teacher);
            return sessionDetail;
        }).collect(Collectors.toList());
        return new PageImpl<>(result,pageable, page.getTotalElements());
    }

    @Override
    public List<SessionDetail> getAllSessionDetailsBySessionId(Long sessionId) {
        return sessionDetailRepository.findAllBySessionId(sessionId).stream().map(sessionDetail -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+session.getStudentId(),User.class);
            User teacher=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+sessionDetail.getTeacherId(),User.class);

            sessionDetail.setTeacherModel(teacher);
            return sessionDetail;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<SessionDetail> getAllSessionDetailsBySessionIdPage(Long sessionId, Pageable pageable) {
        Page<SessionDetail> page=sessionDetailRepository.findAllBySessionId(sessionId,pageable);
        List<SessionDetail> result=page.getContent().stream().map(sessionDetail -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+session.getStudentId(),User.class);
            User teacher=restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/"+sessionDetail.getTeacherId(),User.class);

            sessionDetail.setTeacherModel(teacher);
            return sessionDetail;
        }).collect(Collectors.toList());
        return new PageImpl<>(result,pageable, page.getTotalElements());
    }

/*
    @Override
    public SessionDetail createSessionDetail(Long sessionId, Long teacherId, SessionDetail sessionDetail) {
        return sessionRepository.findById(sessionId).map(session -> {
            return userRepository.findById(teacherId).map(user -> {
                sessionDetail.setTeacher(user);
                sessionDetail.setSession(session);
                return sessionDetailRepository.save(sessionDetail);
            }).orElseThrow(()-> new ResourceNotFoundException("Teacher with Id: "+teacherId+" not found"));
        }).orElseThrow(()-> new ResourceNotFoundException("Session with Id: "+sessionId+" not found"));
    }

    @Override
    public SessionDetail updateSessionDetail(Long sessionId, Long teacherId, Long sessionDetailId, SessionDetail sessionDetailDetails) {
        return sessionRepository.findById(sessionId).map(session -> {
            return userRepository.findById(teacherId).map(user -> {
                return sessionDetailRepository.findById(sessionDetailId).map(sessionDetail -> {
                    sessionDetail.setTeacher(user);
                    sessionDetail.setSession(session);
                    sessionDetail.setState(sessionDetailDetails.getState());
                    return sessionDetailRepository.save(sessionDetail);
                }).orElseThrow(()-> new ResourceNotFoundException("SessionDetail with Id: "+sessionDetailId+" not found"));
            }).orElseThrow(()-> new ResourceNotFoundException("Teacher with Id: "+teacherId+" not found"));
        }).orElseThrow(()-> new ResourceNotFoundException("Session with Id: "+sessionId+" not found"));
    }

    @Override
    public ResponseEntity<?> deleteSessionDetail(Long sessionId, Long teacherId, Long sessionDetailId) {
        return sessionRepository.findById(sessionId).map(session -> {
            return userRepository.findById(teacherId).map(user -> {
                return sessionDetailRepository.findById(sessionDetailId).map(sessionDetail -> {
                    sessionDetailRepository.delete(sessionDetail);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("SessionDetail with Id: "+sessionDetailId+" not found"));
            }).orElseThrow(()-> new ResourceNotFoundException("Teacher with Id: "+teacherId+" not found"));
        }).orElseThrow(()-> new ResourceNotFoundException("Session with Id: "+sessionId+" not found"));
    }

     */
}
