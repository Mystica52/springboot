package com.example.service;

import com.example.dto.EmailUtil;
import com.example.model.Otp;
import com.example.repository.OtpRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;
import java.util.Random;



@Service
public class EmailService {
    @Autowired
    public OTPService otpService;


    private Otp user;
    @Autowired
    private OtpRepository repository;

    @Autowired
    private UserRepository repo;

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;

    private String template;
    private Map<String, String> replacementParams;

    public EmailService() {



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
        String message = " Your Otp Number is " + getOtpnum();




        EmailUtil.sendEmail(session, toEmail, "TLSEmail Testing Subject", message);

    }

        public int getOtpnum() {


            Random rand = new Random();
            int otpnum = rand.nextInt(100000, 999999);

            return otpnum;
        }

//    public boolean isOTPRequired() {
//        if(getOtpnum() == 0) {
//            return false;
//        }
//        long currentTimeInMillis = System.currentTimeMillis();
//        long otpRequestedTimeMillis = user.getOtpRequestedTime().getTime();
//        if(otpRequestedTimeMillis + OTP_VALID_DURATION < currentTimeInMillis) {
//            // otp expires
//            return false;
//        }
//        return true;
//    }
    @NotEmpty
    private Integer optnum;

    @NotEmpty
    private LocalDateTime otpRequestedTime;

    public void setOptnum(int optnum) {
        this.optnum =optnum;
    }

    public LocalDateTime getOtpRequestedTime() {
        return otpRequestedTime;
    }

    public void setOtpRequestedTime(LocalDateTime otpRequestedTime) {
        this.otpRequestedTime = otpRequestedTime;
    }


}
