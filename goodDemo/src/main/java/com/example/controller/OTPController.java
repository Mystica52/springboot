package com.example.controller;

import com.example.dto.EmailTemplate;
import com.example.dto.UserRegistrationDto;
import com.example.service.EmailService;
import com.example.service.OTPService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.Objects;


@Controller
public class OTPController {


    @Autowired
    public OTPService otpService;
    @Autowired
    public EmailTemplate temp;

    @Autowired
    public EmailService emailService;

    @GetMapping("/generateOtp")
    public String generateOTP() throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
//        int otp = otpService.generateOTP(username);
        //Generate The Template to send OTP
        EmailTemplate template = new EmailTemplate();
//        Map<String,String> replacements = new HashMap<String,String>();
//        replacements.put("user", username);
//        replacements.put("user", username);

       // replacements.put("otpnum", String.valueOf(otp));
       // String message = template.getTemplate(replacements);

        template.sendEmail();

       // emailService.sendOtpMessage("dukumystica20@gmail.com", "OTP -SpringBoot", message);

        return "otppage";
    }

    @RequestMapping(value ="/validateOtp", method = RequestMethod.POST)
    public @ResponseBody String validateOtp(@Valid UserRegistrationDto  otpnum){

        final String SUCCESS = "Entered Otp is valid";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";


        if(Objects.equals(otpnum, temp.getOTP())){
            temp.save(otpnum);
            return "index";
        }
        return "Entered Otp is NOT valid. Please Retry!" + "redirect:/otppage";
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
        //Validate the Otp
//        if(otpnum >= 0){
//
//            int serverOtp = otpService.getOtp(username);
//            if(serverOtp > 0){
//                if(otpnum == serverOtp){
//                    otpService.clearOTP(username);
//
//                    return ("Entered otp is valid");
//
//                }
//                else {
//                    return FAIL ;
//                }
//            }else {
//                return FAIL;
//            }
//        }else {
//            return FAIL;
//        }

    }
}
