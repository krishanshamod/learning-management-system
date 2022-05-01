package com.uok.backend.announcement.email;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.uok.backend.announcement.Announcement;
import com.uok.backend.course.registration.CourseRegistration;
import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.email.*;
import com.uok.backend.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LMSEmailService implements EmailService, Runnable {

    private final EmailDataRetriever retriever;
    private final EmailAuthenticator authenticator;
    private final EmailConfigurator configurator;
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final Logger logger;
    private Announcement announcement;

    @Autowired
    public LMSEmailService(
            EmailDataRetriever retriever,
            EmailAuthenticator authenticator,
            EmailConfigurator configurator,
            CourseRegistrationRepository courseRegistrationRepository,
            Logger logger
    ) {
        this.retriever = retriever;
        this.authenticator = authenticator;
        this.configurator = configurator;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.logger = logger;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    @Override
    public void run() {

        // get course registration in that course
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository
                .findByCourseId(announcement.getCourseId());

        for (CourseRegistration courseRegistration : courseRegistrations) {

            // get email data
            Email emailData = retriever.getEmailData(announcement, courseRegistration.getUser().getEmail());

            HttpRequestWithBody authenticatedEmailRequest = authenticator.authenticateEmail();
            HttpRequestWithBody configuredEmailRequest = configurator.configureEmail(authenticatedEmailRequest, emailData);

            try {
                // send email
                configuredEmailRequest.asJson();
            } catch (UnirestException e) {
                logger.logException(e.getMessage());
            }
        }
    }
}
