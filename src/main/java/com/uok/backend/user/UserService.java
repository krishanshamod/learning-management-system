package com.uok.backend.user;

public interface UserService {

    User getUserByEmail(String email);

    //FIXME
    // forTesting purposes
    void addNewUser(User userData);
    //
}
