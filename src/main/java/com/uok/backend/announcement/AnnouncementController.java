package com.uok.backend.announcement;

import com.uok.backend.announcement.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "announcement")
public class AnnouncementController {

    private AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("addannouncement")
    public ResponseEntity addAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.addAnnouncement(announcement);
    }

    @PostMapping("getannouncements")
    public ResponseEntity getAnnouncementsForACourse(@RequestBody GetAnnouncementRequest getAnnouncementRequest) {
        return announcementService.getAnnouncementsForACourse(getAnnouncementRequest);
    }
}
