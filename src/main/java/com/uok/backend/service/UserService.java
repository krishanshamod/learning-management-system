package com.uok.backend.service;

import com.uok.backend.domain.User;

public interface UserService {
    User getUserByEmail(String email);
}
