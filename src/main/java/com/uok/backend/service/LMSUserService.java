package com.uok.backend.service;

import com.uok.backend.domain.User;
import com.uok.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LMSUserService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public LMSUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findById(email).get();
    }

    //forTesting purposes
    @Override
    public void addNewUser(User userData) {
        userRepository.save(userData);
    }
}
