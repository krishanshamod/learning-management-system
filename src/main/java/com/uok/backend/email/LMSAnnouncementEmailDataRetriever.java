package com.uok.backend.email;

import com.uok.backend.announcement.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LMSAnnouncementEmailDataRetriever {
    public Email getEmailData() {
        return new Email(
                "Excited User <USER@YOURDOMAIN.COM>",
                "pj799571@gmail.com",
                "LMS Announcement",
                "Hello"
        );
    }
}
