package com.uok.backend.announcement;

import com.uok.backend.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LMSAnnouncementService implements AnnouncementService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public AddAnnouncementResponse addAnnouncement(Announcement announcement) {

        // check requested data is present or not
        if(announcement.getId() != null && announcement.getTitle() != null && announcement.getContent() != null) {
            // check if the course is exists or not
            if(courseRepository.findById(announcement.getId()) != null) {
                // need to implement email sending functionality

                // add announcement to the database
                announcementRepository.save(announcement);
                return new AddAnnouncementResponse(true);
            } else {
                return new AddAnnouncementResponse(false);
            }

        } else {
            return new AddAnnouncementResponse(false);
        }
    }

}
