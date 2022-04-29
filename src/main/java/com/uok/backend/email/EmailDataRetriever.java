package com.uok.backend.email;

import com.uok.backend.announcement.Announcement;
import com.uok.backend.user.User;

import java.util.List;

public interface EmailDataRetriever {
    Email getEmailData(List<User> userList, Announcement announcement);
}
