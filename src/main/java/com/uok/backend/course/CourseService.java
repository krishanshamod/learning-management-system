package com.uok.backend.course;

import com.uok.backend.course.Course;

import java.util.List;

public interface CourseService {

    void addNewCourse(Course courseData);
    void addUserToCourse(String userEmail, String courseId);
    List<CourseRegistration> getEnrolledCourses(String userEmail);
    List<Course> getAvailableCourses(String userEmail);

}
