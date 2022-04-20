package com.uok.backend.announcement;

public class GetAnnouncementRequest {

    private String courseId;

    public GetAnnouncementRequest() {
    }

    public GetAnnouncementRequest(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
