package com.uok.backend.domain;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "TEXT")
    private String id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

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

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
