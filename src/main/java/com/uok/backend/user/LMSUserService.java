package com.uok.backend.user;

import com.uok.backend.security.JwtRequestFilter;
import com.uok.backend.security.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LMSUserService implements UserService {

    private final UserRepository userRepository;
    private final TokenValidator tokenValidator;

    @Autowired
    public LMSUserService(UserRepository userRepository, TokenValidator tokenValidator) {
        this.userRepository = userRepository;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public ResponseEntity addUser() {
        String email = getTokenUser().getEmail();

        // if user is not in the database, then add user to the database
        if (userRepository.findById(email).isEmpty()) {
            userRepository.save(getTokenUser());
        }

        return ResponseEntity.ok().build();
    }

    @Override
    @Cacheable(cacheNames = {"userCache"}, key = "#root.target.getTokenUser().getEmail()")
    public ResponseEntity getUser() {
        return ResponseEntity.ok(getTokenUser());
    }

    @Override
    public User getTokenUser() {
        String token = JwtRequestFilter.validatedToken;

        String email = tokenValidator.getEmailFromToken(token);
        String firstName = tokenValidator.getFirstNameFromToken(token);
        String lastName = tokenValidator.getLastNameFromToken(token);
        String role = tokenValidator.getRoleFromToken(token);

        return new User(email, firstName, lastName, role);
    }
}
