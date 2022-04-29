package com.uok.backend.announcement.email;

import com.uok.backend.announcement.Announcement;

public interface EmailService {
    void sendAnnouncementEmail(Announcement announcement);
}
