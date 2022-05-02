package com.uok.backend.announcement;

import com.uok.backend.announcement.email.EmailService;
import com.uok.backend.course.Course;
import com.uok.backend.course.GetCourseResponse;
import com.uok.backend.course.registration.CourseRegistration;
import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.exceptions.AnnouncementAddingFailureException;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.user.UserService;
import com.uok.backend.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LMSAnnouncementService implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    //fixme following parameter is new
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final Logger logger;
    private final EmailService emailService;
    //fixme folowing parameter is new
    private final UserService userService;

    @Autowired
    public LMSAnnouncementService(
            AnnouncementRepository announcementRepository,
            CourseRegistrationRepository courseRegistrationRepository,
            Logger logger,
            EmailService emailService,
            UserService userService
    ) {
        this.announcementRepository = announcementRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.logger = logger;
        this.emailService = emailService;
        this.userService = userService;
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
            emailService.setAnnouncement(announcement);
            new Thread(emailService).start();

            // add announcement to the database
            announcementRepository.save(announcement);

            return ResponseEntity.ok().build();

        } catch (DataMissingException | AnnouncementAddingFailureException e) {
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

    //fixme new lines
    //Todo add caching
    @Override
    public ResponseEntity getNotificationsForAUser() {

        String email = userService.getTokenUser().getEmail();
        List<CourseRegistration> registrations = courseRegistrationRepository.findAllByUserEmail(email);
        List<GetNotificationsResponse> notifications = new ArrayList<>();
        for (CourseRegistration registration : registrations) {

            announcementRepository.findByCourseId(registration.getCourse().getId())
                    .forEach(announcement -> notifications.add(new GetNotificationsResponse(
                            //fixme fix this to get course name
                            announcement.getCourseId(),
                            announcement.getTitle(),
                            announcement.getContent()
                    )));
        }
        return ResponseEntity.ok(notifications);

    }
}
