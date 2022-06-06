package com.evertix.sessionservice.service.impl;

import com.evertix.sessionservice.client.CourseClient;
import com.evertix.sessionservice.client.UserClient;
import com.evertix.sessionservice.dto.EStatus;
import com.evertix.sessionservice.dto.SessionSaveResource;
import com.evertix.sessionservice.entities.Session;
import com.evertix.sessionservice.entities.SessionDetail;
import com.evertix.sessionservice.model.Course;
import com.evertix.sessionservice.model.User;
import com.evertix.sessionservice.repository.SessionDetailRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionDetailRepository sessionDetailRepository;


    @Autowired
    private CourseClient courseClient;

    @Autowired
    private UserClient userClient;
    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll().stream().map(session -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+session.getStudentId(),User.class);
            User student=userClient.getUserById(session.getStudentId());
            Course course=courseClient.getCourseById(session.getCourseId());

            session.setStudentModel(student);
            session.setCourseModel(course);
            return session;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<Session> getAllSessionsPage(Pageable pageable) {
        Page<Session> page=sessionRepository.findAll(pageable);
        List<Session> result=page.getContent().stream().map(session -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+session.getStudentId(),User.class);
            User student=userClient.getUserById(session.getStudentId());
            Course course=courseClient.getCourseById(session.getCourseId());

            session.setStudentModel(student);
            session.setCourseModel(course);
            return session;
        }).collect(Collectors.toList());
        return new PageImpl<>(result,pageable, page.getTotalElements());
    }

    @Override
    public Session createSession(Long courseId, Long studentId, SessionSaveResource sessionResource) {

       Course course = courseClient.getCourseById(courseId);
       Session session = new Session();
       session.setCourseId(course.getId());

       User user = userClient.getUserById(studentId);
       session.setStudentId(user.getId());

       session.setStatus(sessionResource.getStatus().name());
       session.setEnd_at(sessionResource.getEnd_at());
       session.setStart_at(sessionResource.getStart_at());
       session.setTopic(sessionResource.getTopic());

       return this.sessionRepository.save(session);
    }

    @Override
    public SessionDetail applySession(Long teacherId, Long sessionId) {
        Optional<Session> session = this.sessionRepository.findById(sessionId);
        if (session.isPresent()) {
            SessionDetail sessionDetail = new SessionDetail();
            sessionDetail.setSession(session.get());
            User user = userClient.getUserById(teacherId);
            sessionDetail.setTeacherId(user.getId());
            sessionDetail.setState("NO");
            return this.sessionDetailRepository.save(sessionDetail);
        }
        return null;
    }

    @Override
    public SessionDetail acceptTeacher(Long teacherId, Long sessionId) {
        Optional<Session> session = this.sessionRepository.findById(sessionId);
        if (session.isPresent()) {
            List<SessionDetail> sessionDetailList = sessionDetailRepository.findAllBySessionId(sessionId);
            SessionDetail teacherSessionDetail = sessionDetailList.stream().filter(x -> x.getTeacherId().equals(teacherId)).findFirst().orElse(null);
            if (teacherSessionDetail != null) {
                teacherSessionDetail.setState("SI");
                session.get().setStatus(EStatus.CLOSED.name());
                this.sessionRepository.save(session.get());
                return this.sessionDetailRepository.save(teacherSessionDetail);
            }
        }
        return null;
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
