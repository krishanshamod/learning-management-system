package com.uok.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("adduser")
    public ResponseEntity addUser() {
        return userService.addUser();
    }

    @GetMapping("getuser")
    public ResponseEntity getUser() {
        return userService.getUser();
    }

}
