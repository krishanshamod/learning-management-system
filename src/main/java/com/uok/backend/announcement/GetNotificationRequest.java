package com.uok.backend.announcement;

public class GetNotificationRequest {

    private String courseId;
    private String title;

    public GetNotificationRequest() {
    }

    public GetNotificationRequest(String courseId, String title) {
        this.courseId = courseId;
        this.title = title;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
