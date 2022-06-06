package com.evertix.sessionservice.client;

import com.evertix.sessionservice.model.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course", url = "${tutofast.course.endpoint}")
public interface CourseClient {

     @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/courses/{courseId}")
     Course getCourseById(@PathVariable Long courseId);



}
