package com.uok.backend.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity addUser();

    ResponseEntity getUser();

    User getTokenUser();

}
