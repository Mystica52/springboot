package com.example.controller;

import com.example.service.EmailService;
import com.example.repository.OtpRepository;
import com.example.service.OTPService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class OTPController {

    @Autowired
    public OTPService otpService;


    @Autowired
    public OtpRepository repo;


    @GetMapping("/generateOtp")
    public String generateOTP() throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
//        int otp = otpService.generateOTP(username);
        //Generate The Template to send OTP
        EmailService template = new EmailService();
        template.sendEmail();
        otpService.save(template);

        return "otppage";
    }

    @PostMapping("/validateOtp")
    public @ResponseBody String validateOtp(@RequestParam("otpnum") EmailService otp, HttpSession session) {

        int myOtp = (int) session.getAttribute("optnum");

        if (otp.getOtpnum() == myOtp) {
            return "redirect:/default";

        } else {


            return "otppage";
        }
    }
//        public final static String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";
//
//        private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//
//        @GetMapping("/code/sms")
//        public void createSmsCode(HttpServletRequest request, HttpServletResponse response, String email) throws IOException {
//
//            Otp otp = createOtp();
//            sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_SMS_CODE +email, otp);
//            //  Output verification code to console instead of SMS sending service
//            System.out.println(" Your login verification code is ：" + otp.getOtpnum() + ", The effective time is 60 second ");
//        }
//
//        private Otp createOtp() {
//
//            Random rand = new Random();
//            int otpnum = rand.nextInt(100000, 999999);
//
//
//            return new Otp(otpnum, 60);
//        }



}

