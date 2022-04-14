package com.uok.backend.announcement;

import java.io.Serializable;

public class AnnouncementId implements Serializable {
    private String title;
    private String courseId;

    public AnnouncementId() {
    }

    public AnnouncementId(String title, String courseId) {
        this.title = title;
        this.courseId = courseId;
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
