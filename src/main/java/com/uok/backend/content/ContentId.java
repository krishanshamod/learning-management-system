package com.uok.backend.content;

import java.io.Serializable;

public class ContentId implements Serializable {
    private String courseId;
    private String title;

    public ContentId() {

    }

    public ContentId(String courseId, String title) {
        this.courseId = courseId;
        this.title = title;
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
