package com.uok.backend.announcement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getannouncements/{courseId}")
    public List<Announcement> getAnnouncementsForACourse(@PathVariable String courseId) {
        return announcementService.getAnnouncementsForACourse(courseId);
    }
}
