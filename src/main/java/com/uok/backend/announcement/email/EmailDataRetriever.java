package com.uok.backend.announcement.email;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.email.Email;

public interface EmailDataRetriever {
    Email getEmailData(Announcement announcement, String studentEmail);
}
