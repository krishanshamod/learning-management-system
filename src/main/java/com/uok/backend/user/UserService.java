package com.uok.backend.user;

public interface UserService {

    User getUserByEmail(String email);

    // FIXME : for testing only
    void addNewUser(User userData);
}
