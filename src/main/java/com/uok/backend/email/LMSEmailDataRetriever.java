package com.uok.backend.email;

import org.springframework.stereotype.Component;

@Component
public class LMSEmailDataRetriever {
    public Email getEmailData() {
        return new Email(
                "Excited User <USER@YOURDOMAIN.COM>",
                "pj799571@gmail.com",
                "LMS Announcement",
                "Hello"
        );
    }
}
