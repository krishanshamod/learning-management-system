package com.uok.backend.mark;


import com.uok.backend.course.registration.CourseRegistration;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MarkService {
    ResponseEntity addCourseMarks(AddMarksRequest addMarksRequest);
    Integer getMarksForACourse(String userEmail , String courseId);
    List<CourseRegistration> getMarksForUser(String userEmail);
}
