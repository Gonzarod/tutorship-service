package com.evertix.tutorshipservice.service;


import com.evertix.tutorshipservice.entities.SessionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SessionDetailService {
    List<SessionDetail> getAllSessionDetails();
    Page<SessionDetail> getAllSessionDetailsPage(Pageable pageable);

    List<SessionDetail> getAllSessionDetailsBySessionId(Long sessionId);
    Page<SessionDetail> getAllSessionDetailsBySessionIdPage(Long sessionId, Pageable pageable);
    /*
    SessionDetail createSessionDetail(Long sessionId, Long teacherId, SessionDetail sessionDetail);
    SessionDetail updateSessionDetail(Long sessionId, Long teacherId, Long sessionDetailId, SessionDetail sessionDetailDetails);
    ResponseEntity<?> deleteSessionDetail(Long sessionId, Long teacherId, Long sessionDetailId);
    */

}
