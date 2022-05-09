package com.uok.backend.announcement;

import com.uok.backend.announcement.email.EmailService;
import com.uok.backend.course.CourseEnrollRequest;
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

    @PostMapping("getnotification")
    public ResponseEntity getNotification(@RequestBody GetNotificationRequest getNotificationRequest) {
        return announcementService.getNotification(getNotificationRequest);
    }

    @GetMapping("getnotifications")
    public ResponseEntity getNotificationsForAUser() {
        return announcementService.getNotificationsForAUser();
    }
}
