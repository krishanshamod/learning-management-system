package com.uok.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //should implement jwt
    @GetMapping("{email}")
    public User getUserByToken(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    //forTesting purposes
    @PostMapping()
    public void registerNewUser(@RequestBody User userData) {
        userService.addNewUser(userData);
    }

}
