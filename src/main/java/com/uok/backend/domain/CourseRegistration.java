package com.uok.backend.domain;

import javax.persistence.*;
import java.util.Optional;

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
