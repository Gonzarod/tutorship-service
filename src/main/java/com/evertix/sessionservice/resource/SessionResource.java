package com.evertix.sessionservice.resource;

import lombok.Data;

import java.util.Date;

@Data
public class SessionResource {
    private Long id;
    private Date start_at;
    private Date end_at;
    private String status;
    private String topic;
    private String link;
    private Long studentId;
    private Long courseId;
}
