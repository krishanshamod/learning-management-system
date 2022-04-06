package com.uok.backend.service;

import com.uok.backend.domain.User;
import com.uok.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findById(email).get();
    }
}
