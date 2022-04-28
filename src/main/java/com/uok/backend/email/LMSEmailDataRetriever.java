package com.uok.backend.email;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.announcement.AnnouncementService;
import com.uok.backend.mark.GetMarksRequest;
import com.uok.backend.mark.MarkService;
import com.uok.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LMSEmailDataRetriever implements EmailDataRetriever {

    private final Environment env;
    private final String fromAddress;
    private final MarkService markService;


    @Autowired
    public LMSEmailDataRetriever(Environment env, MarkService markService) {
        this.env = env;
        this.fromAddress = env.getProperty("email.sending.domain");
        this.markService = markService;
    }


    public Email getEmailData(Announcement announcement) {
        //fixme : use consistent methods
        List<User> userList = (List<User>) markService.getEnrolledStudents(new GetMarksRequest(announcement.getCourseId())).getBody();

        StringBuilder emailList = new StringBuilder();

        for (User user : userList) {
            emailList.append(user.getEmail()).append(",");
        }

        return new Email(
                fromAddress,
                emailList.toString(),
                announcement.getCourseId() + announcement.getTitle(),
                announcement.getContent()
        );
    }
}
