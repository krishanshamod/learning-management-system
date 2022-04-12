package com.uok.backend.course;

import com.uok.backend.course.Course;
import com.uok.backend.user.User;

import javax.persistence.*;

@Entity
@IdClass(CourseRegistrationId.class)
public class CourseRegistration {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @Column(name = "marks", nullable = true)
    private Integer marks;
    @Transient
    private char grade;

    public CourseRegistration(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public CourseRegistration() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    //TODO
    // should implement the proper method
    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
