package com.uok.backend.course;

public class CourseEnrollRequest {
    private String courseId;

    public CourseEnrollRequest() {
    }

    public CourseEnrollRequest(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
