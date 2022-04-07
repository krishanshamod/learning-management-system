package com.uok.backend.course;

import com.uok.backend.course.Course;
import com.uok.backend.user.User;

import javax.persistence.*;

@Entity
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_email")
    User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    Integer marks;

    //should add grade in future


    public CourseRegistration(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public CourseRegistration() {

    }
}
