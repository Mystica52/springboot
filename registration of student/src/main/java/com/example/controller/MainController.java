package com.example.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller

public class MainController {
//    @RequestMapping("/default")
//    public String defaultAfterLogin(HttpServletRequest request) {
//        if (request.isUserInRole("USER")) {
//            return "redirect:/users";
//            }
//        return "redirect:/list";
//        }
@RequestMapping(value= {"/default"}, method = RequestMethod.GET)
public String defaultAfterLogin() {
    Collection<? extends GrantedAuthority> authorities;
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    authorities = auth.getAuthorities();
    String myRole = authorities.toArray()[0].toString();
    String admin = "ADMIN";
    if (myRole.equals(admin)) {
        return "redirect:/list";
    }
    return "redirect:/users";
}


//    @GetMapping()
//    public String root() {
//        return "index";
//    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
