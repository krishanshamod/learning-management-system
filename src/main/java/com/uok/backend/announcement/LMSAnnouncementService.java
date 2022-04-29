package com.uok.backend.announcement;

import com.uok.backend.announcement.email.EmailService;
import com.uok.backend.exceptions.AnnouncementAddingFailureException;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LMSAnnouncementService implements AnnouncementService {

    private AnnouncementRepository announcementRepository;
    private Logger logger;
    private EmailService emailService;

    @Autowired
    public LMSAnnouncementService(
            AnnouncementRepository announcementRepository,
            Logger logger,
            EmailService emailService
    ) {
        this.announcementRepository = announcementRepository;
        this.logger = logger;
        this.emailService = emailService;
    }

    @Override
    @CacheEvict(cacheNames = {"announcementCache"}, key = "#announcement.courseId")
    public ResponseEntity addAnnouncement(Announcement announcement) {

        try {
            // check requested data is received or not
            if(announcement.getCourseId() == null || announcement.getTitle() == null
                    || announcement.getContent() == null) {

                throw new DataMissingException("Input Data missing");
            }

            // check if the announcement is already exists or not
            if(announcementRepository.findByCourseIdAndTitle(
                    announcement.getCourseId(), announcement.getTitle()) != null) {

                throw new AnnouncementAddingFailureException("Announcement already exists");
            }

            // email the announcement to the enrolled students
            emailService.sendAnnouncementEmail(announcement);

            // add announcement to the database
            announcementRepository.save(announcement);

            return ResponseEntity.ok().build();

        } catch (DataMissingException | AnnouncementAddingFailureException | RuntimeException e) {
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @Cacheable(cacheNames = {"announcementCache"}, key = "#getAnnouncementRequest.courseId")
    public ResponseEntity getAnnouncementsForACourse(GetAnnouncementRequest getAnnouncementRequest) {

        try {
            // check all the data received or not
            if (getAnnouncementRequest.getCourseId() == null) {
                throw new DataMissingException("Course Id is missing");
            }

            return ResponseEntity.ok(announcementRepository.findByCourseId(getAnnouncementRequest.getCourseId()));

        } catch (DataMissingException e) {
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
