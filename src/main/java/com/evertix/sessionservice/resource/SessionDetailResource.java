package com.evertix.sessionservice.resource;

import com.evertix.sessionservice.entities.Session;
import lombok.Data;


@Data
public class SessionDetailResource {
    private Long id;
    private String state;
    private Session session;
    private Long teacherId;
}
