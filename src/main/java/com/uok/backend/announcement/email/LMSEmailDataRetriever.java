package com.uok.backend.announcement.email;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.course.CourseRepository;
import com.uok.backend.email.Email;
import com.uok.backend.mark.GetMarksRequest;
import com.uok.backend.mark.MarkService;
import com.uok.backend.security.JwtRequestFilter;
import com.uok.backend.security.TokenValidator;
import com.uok.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LMSEmailDataRetriever implements EmailDataRetriever {

    private final Environment env;
    private final String fromAddress;
    private final CourseRepository courseRepository;
    private final MarkService markService;
    private final TokenValidator tokenValidator;

    @Autowired
    public LMSEmailDataRetriever(
            Environment env,
            CourseRepository courseRepository,
            MarkService markService,
            TokenValidator tokenValidator
    ) {
        this.env = env;
        this.fromAddress = env.getProperty("email.sending.domain");
        this.courseRepository = courseRepository;
        this.markService = markService;
        this.tokenValidator = tokenValidator;
    }

    public Email getEmailData(Announcement announcement) {

        // get users who enrolled in the course
        List<User> userList = (List<User>) markService.getEnrolledStudents(new GetMarksRequest(announcement.getCourseId())).getBody();

        // convert users list as a string
        StringBuilder emailList = new StringBuilder();

        for (User user : userList) {
            emailList.append(user.getEmail()).append(",");
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
