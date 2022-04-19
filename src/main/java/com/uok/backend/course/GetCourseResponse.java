package com.uok.backend.course;

public class GetCourseResponse {
    private String id;
    private String name;

    public GetCourseResponse() {
    }

    public GetCourseResponse(String id, String name) {
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
}
