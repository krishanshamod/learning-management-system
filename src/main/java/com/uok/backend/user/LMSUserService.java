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

        return ResponseEntity.ok(userRepository.findById(email).get());
    }

    //FIXME
    // forTesting purposes
    @Override
    public void addNewUser(User userData) {
        userRepository.save(userData);
    }
}
