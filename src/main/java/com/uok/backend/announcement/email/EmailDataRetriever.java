package com.uok.backend.announcement.email;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.email.Email;
import com.uok.backend.user.User;

import java.util.List;

public interface EmailDataRetriever {
    Email getEmailData(Announcement announcement);
}
