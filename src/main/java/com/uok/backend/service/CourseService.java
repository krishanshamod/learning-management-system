package com.uok.backend.service;

import com.uok.backend.domain.Course;

public interface CourseService {

    void addNewCourse(Course courseData);
    void addUserToCourse(String userEmail, String courseId);

}
