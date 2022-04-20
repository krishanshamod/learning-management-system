package com.uok.backend.exceptions;

public class AnnouncementAddingFailureException extends Exception {

    public AnnouncementAddingFailureException() {
        super("Announcement adding failure");
    }

    public AnnouncementAddingFailureException(String message) {
        super(message);
    }
}
