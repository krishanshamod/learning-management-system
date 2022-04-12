package com.uok.backend.mark;


public interface MarkService {
    void addCourseMarks(String courseId, String userEmail, Integer mark);
}
