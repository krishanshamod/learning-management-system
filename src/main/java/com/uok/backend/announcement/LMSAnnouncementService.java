package com.uok.backend.announcement;

import com.uok.backend.announcement.email.EmailService;
import com.uok.backend.announcement.observer.AnnouncementEmailObserver;
import com.uok.backend.announcement.observer.AnnouncementSaveObserver;
import com.uok.backend.announcement.observer.Subject;
import com.uok.backend.course.registration.CourseRegistration;
import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.exceptions.AnnouncementAddingFailureException;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.exceptions.GetNotificationFailureException;
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
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final Logger logger;
    private final EmailService emailService;
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

            // subscribe add announcement observers to the list
            Subject subject = new Subject();
            subject.subscribe(new AnnouncementEmailObserver(emailService));
            subject.subscribe(new AnnouncementSaveObserver(announcementRepository));

            // notify all observers
            subject.notifyAllObservers(announcement);

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

    @Override
    @Cacheable(cacheNames = {"notificationCache"}, key = "{#getNotificationRequest.courseId, #getNotificationRequest.title}")
    public ResponseEntity getNotification(GetNotificationRequest getNotificationRequest) {

        try {
            // check all the data received or not
            if (getNotificationRequest.getCourseId() == null || getNotificationRequest.getTitle() == null) {
                throw new DataMissingException("Course Id or Title is missing");
            }

            // get the announcement
            Announcement announcement = announcementRepository.findByCourseIdAndTitle(
                    getNotificationRequest.getCourseId(),
                    getNotificationRequest.getTitle()
            );

            // check if the announcement is exists or not
            if (announcement == null) {
                throw new GetNotificationFailureException("Announcement not found");
            }

            return ResponseEntity.ok(announcement);

        } catch (DataMissingException | GetNotificationFailureException e) {
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity getNotificationsForAUser() {

        String email = userService.getTokenUser().getEmail();

        // get all the courses registered by the user
        List<CourseRegistration> registrations = courseRegistrationRepository.findAllByUserEmail(email);

        List<GetNotificationsResponse> notifications = new ArrayList<>();

        // get all the announcements for each course and add to the list
        for (CourseRegistration registration : registrations) {
            announcementRepository.findByCourseId(registration.getCourse().getId())
                    .forEach(announcement -> notifications.add(new GetNotificationsResponse(
                            registration.getCourse().getName(),
                            announcement.getCourseId(),
                            announcement.getTitle()
                    )));
        }
        return ResponseEntity.ok(notifications);
    }
}
