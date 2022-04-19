package com.uok.backend.course;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {

    ResponseEntity addNewCourse(Course courseData);
    ResponseEntity addUserToCourse(CourseEnrollRequest courseEnrollRequest);
    ResponseEntity getEnrolledCourses();
    List<Course> getAvailableCourses(String userEmail);

}
