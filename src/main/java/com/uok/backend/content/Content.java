package com.uok.backend.content;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(ContentId.class)
public class Content {

    @Id
    @Column(name = "course_id")
    private String courseId;
    @Id
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @CreationTimestamp
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    public Content() {

    }

    public Content(String courseId, String title, String content) {
        this.courseId = courseId;
        this.title = title;
        this.content = content;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String id) {
        this.courseId = id;
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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
