package com.uok.backend.mark;


import com.uok.backend.course.registration.CourseRegistration;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MarkService {
    ResponseEntity addCourseMarks(AddMarksRequest addMarksRequest);
    ResponseEntity getMarksForACourse(GetMarksRequest getMarksRequest);
    List<CourseRegistration> getMarksForUser(String userEmail);
}
