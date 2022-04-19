package com.uok.backend.course;

import org.springframework.http.ResponseEntity;

public interface CourseService {

    ResponseEntity addNewCourse(Course courseData);
    ResponseEntity addUserToCourse(CourseEnrollRequest courseEnrollRequest);
    ResponseEntity getEnrolledCourses();
    ResponseEntity getAvailableCourses();

}
