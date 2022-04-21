package com.uok.backend.mark;

public class GetMarksRequest {
    private String courseId;

    public GetMarksRequest() {
    }

    public GetMarksRequest(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

