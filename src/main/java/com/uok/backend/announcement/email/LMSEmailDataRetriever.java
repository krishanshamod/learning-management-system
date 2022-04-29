package com.uok.backend.announcement.email;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.course.CourseRepository;
import com.uok.backend.course.registration.CourseRegistration;
import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.email.Email;
import com.uok.backend.security.JwtRequestFilter;
import com.uok.backend.security.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LMSEmailDataRetriever implements EmailDataRetriever {

    private final Environment env;
    private final String fromAddress;
    private final CourseRepository courseRepository;
    private final TokenValidator tokenValidator;
    private final CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    public LMSEmailDataRetriever(
            Environment env,
            CourseRepository courseRepository,
            TokenValidator tokenValidator,
            CourseRegistrationRepository courseRegistrationRepository
    ) {
        this.env = env;
        this.fromAddress = env.getProperty("email.sending.domain");
        this.courseRepository = courseRepository;
        this.tokenValidator = tokenValidator;
        this.courseRegistrationRepository = courseRegistrationRepository;
    }

    public Email getEmailData(Announcement announcement) {

        // get course registration in that course
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository
                .findByCourseId(announcement.getCourseId());

        // convert users list as a string
        StringBuilder emailList = new StringBuilder();

        for (CourseRegistration courseRegistration : courseRegistrations) {
            emailList.append(courseRegistration.getUser().getEmail()).append(",");
        }

        // get course name
        String courseName = courseRepository.findById(announcement.getCourseId()).get().getName();

        // get lecturer name
        String token = JwtRequestFilter.validatedToken;
        String firstName = tokenValidator.getFirstNameFromToken(token);
        String lastName = tokenValidator.getLastNameFromToken(token);
        String fullName = firstName + " " + lastName;

        String subject = announcement.getTitle();

        String body =
                "Course: " + courseName + "\n\n" +
                announcement.getContent() + "\n\n" +
                "Best Regards,\n" +
                fullName;

        return new Email(
                fromAddress,
                emailList.toString(),
                subject,
                body
        );
    }
}
