package com.uok.backend.announcement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, AnnouncementId> {
    List<Announcement> findByCourseId(String courseId);

    Announcement findByCourseIdAndTitle(String courseId, String title);

}
