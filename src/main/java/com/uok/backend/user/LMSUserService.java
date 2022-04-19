package com.uok.backend.user;

import com.uok.backend.security.JwtRequestFilter;
import com.uok.backend.security.TokenValidator;
import com.uok.backend.user.User;
import com.uok.backend.user.UserRepository;
import com.uok.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity getUser() {
        String token = JwtRequestFilter.validatedToken;
        String email = tokenValidator.getEmailFromToken(token);
        String firstName = tokenValidator.getFirstNameFromToken(token);
        String lastName = tokenValidator.getLastNameFromToken(token);
        String role = tokenValidator.getRoleFromToken(token);

        // if user is not in the database, then add user to the database
        if (userRepository.findById(email).isEmpty()) {
            userRepository.save(new User(email, firstName, lastName, role));
        }

        return ResponseEntity.ok(new User(email, firstName, lastName, role));
    }

    //FIXME
    // forTesting purposes
    @Override
    public void addNewUser(User userData) {
        userRepository.save(userData);
    }
}
