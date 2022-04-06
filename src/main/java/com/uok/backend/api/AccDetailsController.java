package com.uok.backend.api;

import com.uok.backend.service.UserService;
import com.uok.backend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccDetailsController {
    private UserService userService;

    @Autowired
    public AccDetailsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{email}")
    public User getUser(@PathVariable String email) {
        return userService.getUser(email);
    }
}
