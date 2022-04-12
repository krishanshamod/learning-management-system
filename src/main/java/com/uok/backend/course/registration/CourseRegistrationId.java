package com.uok.backend.course.registration;

import com.uok.backend.course.Course;
import com.uok.backend.user.User;

import java.io.Serializable;

public class CourseRegistrationId implements Serializable {
    private User user;

    private Course course;

    public CourseRegistrationId() {

    }

    public CourseRegistrationId(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
