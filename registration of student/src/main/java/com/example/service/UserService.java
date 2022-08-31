package com.example.service;

import com.example.dto.UserRegistrationDto;
import com.example.model.Role;
import com.example.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {




    User findByEmail(String email);
    //Otp findByOptnum(int optnum);


    User save(UserRegistrationDto registration);




    Role get(Role role);

      List<Role> listAll();

    //void sendLoginConfirmationEmail(User user);


}
