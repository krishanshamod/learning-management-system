package com.uok.backend.Content;

public class GetContentRequest {
    private String courseId;

    public GetContentRequest() {
    }

    public GetContentRequest(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
