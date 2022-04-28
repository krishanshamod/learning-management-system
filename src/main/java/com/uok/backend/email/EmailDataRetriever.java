package com.uok.backend.email;

import com.uok.backend.announcement.Announcement;

public interface EmailDataRetriever {
    Email getEmailData(Announcement announcement);
}
