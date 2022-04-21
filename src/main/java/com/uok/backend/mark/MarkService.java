package com.uok.backend.mark;

import org.springframework.http.ResponseEntity;

public interface MarkService {
    ResponseEntity addCourseMarks(AddMarksRequest addMarksRequest);
    ResponseEntity getMarksForACourse(GetMarksRequest getMarksRequest);
    ResponseEntity getStudentMarksForACourse(GetStudentMarksRequest getStudentMarksRequest);
    ResponseEntity getMarksForUser();
    ResponseEntity getEnrolledStudents(GetMarksRequest getMarksRequest);
}
