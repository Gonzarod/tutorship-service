package com.evertix.sessionservice.config;

import com.evertix.sessionservice.entities.Session;
import com.evertix.sessionservice.entities.SessionDetail;
import com.evertix.sessionservice.repository.SessionDetailRepository;
import com.evertix.sessionservice.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader {

    private SessionRepository sessionRepository;
    private SessionDetailRepository sessionDetailRepository;

    @Autowired
    public DataLoader(SessionRepository sessionRepository, SessionDetailRepository sessionDetailRepository) {
        this.sessionRepository=sessionRepository;
        this.sessionDetailRepository=sessionDetailRepository;
        LoadData();
    }

    private void LoadData() {
        LocalDateTime start_at = LocalDateTime.of(2020,Month.OCTOBER,5,0,0,0);
        LocalDateTime end_at = LocalDateTime.of(2020, Month.OCTOBER,5,11,0,0);

        Session session = new Session(Date.from(start_at.toInstant(ZoneOffset.UTC)),
                                      Date.from(end_at.toInstant(ZoneOffset.UTC)),
                                "Pendiente","Integrales","www.skype.com/a12",(long) 1,(long) 1);

        this.sessionRepository.save(session);

        
        SessionDetail sessionDetail = new SessionDetail("Aceptación Pendiente", (long) 2);
        sessionDetail.setSession(session);
        this.sessionDetailRepository.save(sessionDetail);

    }
}