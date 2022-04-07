package com.uok.backend.course;

import com.uok.backend.course.Course;

public interface CourseService {

    void addNewCourse(Course courseData);
    void addUserToCourse(String userEmail, String courseId);

}
