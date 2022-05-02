package com.uok.backend.announcement;

public class GetNotificationsRequest {

    private String userEmail;

    public GetNotificationsRequest() {
    }

    public GetNotificationsRequest(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
