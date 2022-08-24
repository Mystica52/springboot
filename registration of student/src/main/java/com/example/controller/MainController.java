package com.example.controller;


import com.example.dto.UserRegistrationDto;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

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
    String user="USER";
    if (myRole.equals(admin)) {

        return "redirect:/list";
    }
    else if (myRole.equals(user)) {

        return "redirect:/users";
    }
    return "redirect:/registration";
}

//    @Autowired
//    private UserService userService;
////@RequestMapping(value= {"/default"}, method = RequestMethod.GET)
////public String registerUserRole(@ModelAttribute("role") @Valid UserRegistrationDto userDto){
////    Collection<? extends GrantedAuthority> authorities;
////    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////    authorities = auth.getAuthorities();
////    String myRole = authorities.toArray()[0].toString();
////    Optional<User> role= userService.get(userDto.getRole());
////    String admin="ADMIN";
////    String user="USER";
////    String user1= String.valueOf(myRole.equals(admin));
////    String user2= String.valueOf(myRole.equals(user));
////    if (role.equals(user1)){
////        return "redirect:/list";
////    } else if (role.equals(user2)) {
////        return "redirect:/users";
////
////    }
////    return "redirect:/registration";
////}


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