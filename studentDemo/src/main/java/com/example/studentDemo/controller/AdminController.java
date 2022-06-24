package com.example.studentDemo.controller;

import com.example.studentDemo.model.User;
import com.example.studentDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/api/")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin/add")
    public  String addUserByAdmin(@RequestBody User user){
        String pwd= user.getPassword();
        String encrytPwd= passwordEncoder.encode(pwd);
        user.setPassword(encrytPwd);
        userRepository.save(user);
        return " user added successfully..";
    }

}
