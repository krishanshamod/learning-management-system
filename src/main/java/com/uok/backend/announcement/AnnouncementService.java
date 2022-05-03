package com.uok.backend.announcement;

import org.springframework.http.ResponseEntity;

public interface AnnouncementService {
    ResponseEntity addAnnouncement(Announcement announcement);

    ResponseEntity getAnnouncementsForACourse(GetAnnouncementRequest getAnnouncementRequest);

    ResponseEntity getNotification(GetNotificationRequest getNotificationRequest);

    ResponseEntity getNotificationsForAUser();
}
