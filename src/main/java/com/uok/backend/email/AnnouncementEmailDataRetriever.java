package com.uok.backend.email;

import com.uok.backend.announcement.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementEmailDataRetriever {

    private final AnnouncementRepository announcementRepository;
    private Email emailData;

    @Autowired
    public AnnouncementEmailDataRetriever(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Email getEmailData() {
        return emailData;
    }
}
