package com.uok.backend.user;

import com.uok.backend.user.User;
import com.uok.backend.user.UserRepository;
import com.uok.backend.user.UserService;
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

    //FIXME
    // forTesting purposes
    @Override
    public void addNewUser(User userData) {
        userRepository.save(userData);
    }
    //
}
