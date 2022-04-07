package com.uok.backend.course;

import com.uok.backend.course.Course;
import com.uok.backend.user.User;

import javax.persistence.*;

@Entity
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    //should implement the proper method
    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
