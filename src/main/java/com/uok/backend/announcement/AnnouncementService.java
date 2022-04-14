package com.uok.backend.announcement;

import java.util.List;

public interface AnnouncementService {
    public AddAnnouncementResponse addAnnouncement(Announcement announcement);

    List<Announcement> getAnnouncementsForACourse(String CourseId);
}
