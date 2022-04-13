package com.uok.backend.announcement;

import com.uok.backend.course.Course;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Announcement {

    @Id
    @Column(name = "course_id")
    private String id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    public Announcement() {
    }

    public Announcement(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Announcement(String id, Course course, String title, String content, LocalDateTime timeStamp) {
        this.id = id;
        this.course = course;
        this.title = title;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
