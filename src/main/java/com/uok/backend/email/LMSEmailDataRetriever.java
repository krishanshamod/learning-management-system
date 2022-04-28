package com.uok.backend.email;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.announcement.AnnouncementService;
import com.uok.backend.mark.GetMarksRequest;
import com.uok.backend.mark.MarkService;
import com.uok.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LMSEmailDataRetriever {
    // FIXME: implement interface EmailDataRetriever

    private AnnouncementService announcementService;
    private MarkService markService;

    @Autowired
    public LMSEmailDataRetriever(AnnouncementService announcementService, MarkService markService) {
        this.announcementService = announcementService;
        this.markService = markService;
    }


    public Email getEmailData(Announcement announcement) {
        //fixme : use consistent methods
        List<User> userList =(List<User>) markService.getEnrolledStudents(new GetMarksRequest(announcement.getCourseId())).getBody();

        StringBuilder emailList = new StringBuilder("");

        for(User user : userList) {
            emailList.append(user.getEmail()).append(",");
        }

        return new Email(
                "leanSpire <learnspire.info@t8.com>",
                emailList.toString(),
                announcement.getCourseId() + announcement.getTitle(),
                announcement.getContent()
        );
    }
}
