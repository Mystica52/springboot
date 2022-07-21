package com.example.dto;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.*;
import java.nio.file.Files;
import java.sql.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Random;




@Component
public class EmailTemplate {
    @Autowired
    public OTPService otpService;


    private User user;

    @Autowired
    private UserRepository repo;

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;

    private String template;
    private Map<String, String> replacementParams;

    public EmailTemplate() {

//        try {
//            this.template = loadTemplate(customtemplate);
//        } catch (Exception e) {
//            this.template = "Empty";
//        }

    }

    private String loadTemplate(String customtemplate) throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(customtemplate).getFile());
        String content = "Empty";
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new Exception("Could not read template  = " + customtemplate);
        }
        return content;

    }

    public String getTemplate(Map<String, String> replacements) {

        String cTemplate = this.template;
        //Replace the String
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return cTemplate;
    }

    public void sendEmail() throws Exception {
        final String fromEmail = "mdukuzeyezu@bk.rw"; //requires valid gmail id
        final String password = "rosaDb10!"; // correct password for gmail id
        final String toEmail = "dukumystica20@gmail.com"; // can be any email id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        String message = " Your Otp Number is " + getOTP();

        EmailUtil.sendEmail(session, toEmail, "TLSEmail Testing Subject", message);
    }
//        StringBuilder contentBuilder = new StringBuilder();
//        try {
//            BufferedReader in = new BufferedReader(new FileReader("/SendOtp.html"));
//            String str;
//            while ((str = in.readLine()) != null) {
//                contentBuilder.append(str);
//            }
//            in.close();
//        } catch (IOException e) {
//        }
//        String content = contentBuilder.toString();
//        @RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
//        public @ResponseBody String validateOt(@RequestParam("otpnum") int otpnum){

//        }
//        int optnum=0;
//        public   String validateOtp(int otpnum){
//
//
//            final String SUCCESS = "Entered Otp is valid";
//            final String FAIL = "Entered Otp is NOT valid. Please Retry!";
//            Authentication authe = SecurityContextHolder.getContext().getAuthentication();
//
//            String username = authe.getName();
//            //Validate the Otp
//            if(otpnum >= 0){
//
//                int serverOtp = otpService.getOtp(username);
//                if(serverOtp > 0){
//                    if(otpnum == serverOtp)
//                        otpService.clearOTP(username);
//
//                        return ("Entered otp is valid");
//                    }
//                    else {
//                        return FAIL ;
//                    }
//                }else {
//                    return FAIL;
//                }
//            }else {
//                return FAIL;
//            }
        public int getOTP() {


            Random rand = new Random();
            int otpnum = rand.nextInt(100000, 999999);

//            user.setOtpnum(otpnum);
//            user.setOtpRequestedTime(new Date(OTP_VALID_DURATION));
//            repo.save(user);
            return otpnum;
        }

    public boolean isOTPRequired() {
        if(this.getOTP() == 0) {
            return false;
        }
        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeMillis = user.getOtpRequestedTime().getTime();
        if(otpRequestedTimeMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            // otp expires
            return false;
        }
        return true;
    }
    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setOtpnum(registration.getOptnum());
        user.setOtpRequestedTime(registration.getOtpRequestedTime());
        return repo.save(user);
    }














}
