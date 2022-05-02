package com.uok.backend.announcement;

//fixme new class
public class GetNotificationsResponse {
    private String courseName;
    private String title;
    private String content;

    public GetNotificationsResponse() {
    }

    public GetNotificationsResponse(String courseName, String title, String content) {
        this.courseName = courseName;
        this.title = title;
        this.content = content;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
