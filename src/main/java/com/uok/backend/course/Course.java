package com.uok.backend.course;

import com.uok.backend.course.registration.CourseRegistration;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "TEXT")
    private String id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @OneToMany(mappedBy = "user")
    private Set<CourseRegistration> registrations;

    public Course() {
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<CourseRegistration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
