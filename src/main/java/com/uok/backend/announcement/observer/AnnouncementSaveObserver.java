package com.uok.backend.announcement.observer;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.announcement.AnnouncementRepository;

public class AnnouncementSaveObserver implements Observer {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementSaveObserver(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public void notifyObserver(Announcement announcement) {

        // add announcement to the database
        announcementRepository.save(announcement);

    }
}
