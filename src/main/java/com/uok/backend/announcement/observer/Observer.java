package com.uok.backend.announcement.observer;

import com.uok.backend.announcement.Announcement;

public interface Observer {
    void notifyObserver(Announcement announcement);
}
