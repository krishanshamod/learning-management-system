package com.uok.backend.announcement;

public class AddAnnouncementResponse {
    private boolean isAdded;

    public AddAnnouncementResponse(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }
}
