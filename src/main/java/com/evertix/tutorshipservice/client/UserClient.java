package com.evertix.tutorshipservice.client;

import com.evertix.tutorshipservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", url = "${tutofast.user.service.endpoint}")
public interface UserClient {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/users/{userId}")
    User getUserById(@PathVariable Long userId);

}
