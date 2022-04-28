package com.uok.backend.announcement;

import com.uok.backend.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "announcement")
public class AnnouncementController {

    private AnnouncementService announcementService;
    private EmailService emailService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService, EmailService emailService) {
        this.announcementService = announcementService;
        this.emailService = emailService;
    }

    @PostMapping("addannouncement")
    public ResponseEntity addAnnouncement(@RequestBody Announcement announcement) {
        emailService.sendAnnouncemetEmail(announcement);
        return announcementService.addAnnouncement(announcement);
    }

    @PostMapping("getannouncements")
    public ResponseEntity getAnnouncementsForACourse(@RequestBody GetAnnouncementRequest getAnnouncementRequest) {
        return announcementService.getAnnouncementsForACourse(getAnnouncementRequest);
    }
}
