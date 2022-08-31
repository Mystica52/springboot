package com.example.controller;

import com.example.model.Otp;
import com.example.model.User;
import com.example.service.EmailService;
import com.example.repository.OtpRepository;
import com.example.service.OTPService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class OTPController {

    @Autowired
    public OTPService otpService;


    @Autowired
    public OtpRepository repo;


    @GetMapping("/generateOtp")
    public String generateOTP(Model model) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
//        int otp = otpService.generateOTP(username);
        //Generate The Template to send OTP
        EmailService template = new EmailService();
        template.sendEmail();
        model.addAttribute( "otp", new Otp());
        model.addAttribute( "user", new User());

        return "otppage";
    }

    @PostMapping("/generateOtp")
    public @ResponseBody String validateOtp(@ModelAttribute("otp") @Valid User otp,
                                            BindingResult result, Model model) throws Exception {

        if (result.hasErrors()) {
            model.addAttribute("noErrors", true);
        }
        model.addAttribute("otp", otp);

        otpService.createVerification(otp.getEmail());
        return "redirect://default";
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
//package com.example.controller;
//
//import com.example.dto.UserRegistrationDto;
//import com.example.model.Otp;
//import com.example.model.User;
//import com.example.service.EmailService;
//import com.example.repository.OtpRepository;
//import com.example.service.OTPService;
//
//import com.example.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import java.sql.Timestamp;
//
//
//@Controller
//public class OTPController {
//
//    @Autowired
//    public OTPService otpService;
//
//    @Autowired
//    public UserService userService;
//
//
//    @Autowired
//    public OtpRepository repo;
//
//
//    @GetMapping("/generateOtp")
//    public String generateOTP(@RequestParam("otp") String otp, Model model){
//        Otp otp4= otpService.findByOtpnum(otp);
//        if (otp4==null) {
//            model.addAttribute("message", "your verification otp is invaild");
//
//        } else {
//
//            User user= otp4.getUser();
//            // if the user account is not activated
//            if(!user.isEnable()){
//                // get the current timestamp
//                Timestamp currentTimestamp= new Timestamp(System.currentTimeMillis());
//                //check if he otp is expired
//                if (otp4.getExpiryDate().before(currentTimestamp)){
//                    model.addAttribute("message", "your verification otp has expired");
//                }else{
//                    //the token is valid
//                    //active the user account
//                    user.setEnable(true);
//                    //update the user
//                    userService.save1(user);
//                    model.addAttribute("message", "your account is successfully activated");
//                }
//
//            }else{
////                the user account is already activated
//                model.addAttribute("message","your account is already activated ");
//
//            }
//
//        }
//
//
//
//        return "activation";
//    }
//
//    @PostMapping("/generateOtp")
//    public String registerUserAccount(@ModelAttribute("otp") @Valid UserRegistrationDto userDto,
//                                      BindingResult result, RedirectAttributes ra) {
//
//
//
//
//        if (result.hasErrors()) {
//            return "login";
//        }
//        ra.addFlashAttribute("message", "Success! Averification email has been sent to your email adress ");
//        return "redirect:/default";
//
//
//
//    }
//}
////        public final static String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";
////
////        private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
////
////        @GetMapping("/code/sms")
////        public void createSmsCode(HttpServletRequest request, HttpServletResponse response, String email) throws IOException {
////
////            Otp otp = createOtp();
////            sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_SMS_CODE +email, otp);
////            //  Output verification code to console instead of SMS sending service
////            System.out.println(" Your login verification code is ：" + otp.getOtpnum() + ", The effective time is 60 second ");
////        }
////
////        private Otp createOtp() {
////
////            Random rand = new Random();
////            int otpnum = rand.nextInt(100000, 999999);
////
////
////            return new Otp(otpnum, 60);
////        }
//
//
//
//
