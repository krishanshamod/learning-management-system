package com.uok.backend.course;

import com.uok.backend.course.registration.CourseRegistration;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {

    ResponseEntity addNewCourse(Course courseData);
    void addUserToCourse(String userEmail, String courseId);
    List<Course> getEnrolledCourses(String userEmail);
    List<Course> getAvailableCourses(String userEmail);

}
