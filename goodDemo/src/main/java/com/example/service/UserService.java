package com.example.service;

import com.example.dto.UserRegistrationDto;
import com.example.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User findByOptnum(int optnum);

    User save(UserRegistrationDto registration);
}
