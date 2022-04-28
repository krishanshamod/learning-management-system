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
        ResponseEntity users = markService.getEnrolledStudents(new GetMarksRequest(announcement.getCourseId()));
        List<User> userList = (List<User>) users.getBody();
        ArrayList<String> emails = new ArrayList<>();
        for(User user : userList) {
            emails.add(user.getEmail());
        }

        System.out.println(Arrays.toString(emails.toArray()));
        return new Email(
                "leanSpire <learnspire.info@t8.com>",
                        "pj799571@gmail.com,jayaward-se18021@stu.kln.ac.lk",
                announcement.getCourseId() + announcement.getTitle(),
                announcement.getContent()
        );
    }


}
