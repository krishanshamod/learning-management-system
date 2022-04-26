package com.uok.backend.announcement;

import com.uok.backend.exceptions.AnnouncementAddingFailureException;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LMSAnnouncementService implements AnnouncementService {

    private AnnouncementRepository announcementRepository;
    private Logger logger;

    @Autowired
    public LMSAnnouncementService(AnnouncementRepository announcementRepository, Logger logger) {
        this.announcementRepository = announcementRepository;
        this.logger = logger;
    }

    @Override
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

            // add announcement to the database
            announcementRepository.save(announcement);

            return ResponseEntity.ok().build();

        } catch (DataMissingException | AnnouncementAddingFailureException e) {
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
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
