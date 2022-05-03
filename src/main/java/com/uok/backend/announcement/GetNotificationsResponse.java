package com.uok.backend.announcement;

public class GetNotificationsResponse {
    private String courseName;
    private String courseId;
    private String title;

    public GetNotificationsResponse() {
    }

    public GetNotificationsResponse(String courseName, String courseId, String title) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.title = title;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
