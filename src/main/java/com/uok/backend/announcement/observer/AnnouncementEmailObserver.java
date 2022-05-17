package com.uok.backend.announcement.observer;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.announcement.email.EmailService;

public class AnnouncementEmailObserver implements Observer {

    private final EmailService emailService;

    public AnnouncementEmailObserver(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void notifyObserver(Announcement announcement) {

        // email the announcement to the enrolled students
        emailService.setAnnouncement(announcement);
        new Thread(emailService).start();

    }
}
