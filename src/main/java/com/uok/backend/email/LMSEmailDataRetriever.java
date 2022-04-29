package com.uok.backend.email;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LMSEmailDataRetriever implements EmailDataRetriever {

    private final Environment env;
    private final String fromAddress;

    @Autowired
    public LMSEmailDataRetriever(Environment env) {
        this.env = env;
        this.fromAddress = env.getProperty("email.sending.domain");
    }

    public Email getEmailData(List<User> userList, Announcement announcement) {

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
