package com.uok.backend.announcement.email;

import com.uok.backend.announcement.Announcement;

public interface EmailService extends Runnable {
    public void setAnnouncement(Announcement announcement);
    public void run();
}
