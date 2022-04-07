package com.uok.backend.user;

public interface UserService {

    User getUserByEmail(String email);

    //forTesting purposes
    void addNewUser(User userData);
    //
}
