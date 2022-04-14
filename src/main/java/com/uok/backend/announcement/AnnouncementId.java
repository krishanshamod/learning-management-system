package com.uok.backend.announcement;

import java.io.Serializable;

public class AnnouncementId implements Serializable {
    private String title;
    private String id;

    public AnnouncementId() {
    }

    public AnnouncementId(String title, String id) {
        this.title = title;
        this.id = id;
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
