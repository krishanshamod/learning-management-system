package com.uok.backend.api;

import com.uok.backend.service.UserService;
import com.uok.backend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class AccDetailsController {
    private UserService userService;

    @Autowired
    public AccDetailsController(UserService userService) {
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
