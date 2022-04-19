package com.uok.backend.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity getUser();

    // FIXME : for testing only
    void addNewUser(User userData);
}
