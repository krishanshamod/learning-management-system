package com.uok.backend.announcement;

import org.springframework.http.ResponseEntity;

public interface AnnouncementService {
    public ResponseEntity addAnnouncement(Announcement announcement);

    ResponseEntity getAnnouncementsForACourse(GetAnnouncementRequest getAnnouncementRequest);
}
