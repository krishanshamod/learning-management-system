package com.uok.backend.announcement;

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
