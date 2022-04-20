package com.uok.backend.announcement;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnnouncementService {
    public ResponseEntity addAnnouncement(Announcement announcement);

    List<Announcement> getAnnouncementsForACourse(String CourseId);
}
