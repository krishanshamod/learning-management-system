package com.uok.backend.mark;


import com.uok.backend.course.registration.CourseRegistration;

import java.util.List;

public interface MarkService {
    void addCourseMarks(String courseId, String userEmail, Integer mark);
    Integer getMarksForACourse(String userEmail , String courseId);
    List<CourseRegistration> getMarksForUser(String userEmail);
}
