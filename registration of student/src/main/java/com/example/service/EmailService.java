package com.example.service;

import com.example.dto.EmailUtil;
import com.example.model.User;
import com.example.repository.OtpRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;



@Service
public class EmailService {
    @NotEmpty
    private  User user;
    @Autowired
    private OTPService otpService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine template;



    @Autowired
    private OtpRepository repository;

    @Autowired
    private UserRepository repo;

//    public void sendEmail(User user) throws MessagingException{
//        Otp otp2=otpService.findByUser(user);
//        //check if the user has a token
//        if (otp2 != null){
//         String otp3= otp2.getOtpnum();
//            Context context= new Context();
//            context.setVariable("title", "verify your email adress");
//            context.setVariable("link", "https://localhost:8080/generateOtp?otp"+ otp3 );
//            //create a HTML template and pass the variable to it
//            String body= template.process("otppage", context);
//
//            //send the verification email
//            MimeMessage message= javaMailSender.createMimeMessage();
//            MimeMessageHelper helper= new MimeMessageHelper(message, true);
//            helper.setTo(user.getEmail());
//            helper.setSubject("email address verification");
//            helper.setText(body, true);
//            javaMailSender.send(message);
//        }
//    }
    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;

    @NotEmpty
    private Integer otpnum;


    public EmailService() {



    }

    public EmailService(LocalDateTime otpRequestedTime, int otpnum, User user) {
        this.otpnum = otpnum;
        this.otpRequestedTime = otpRequestedTime;
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
        LocalDateTime expireTime= LocalDateTime.now();
        int expireIn=5;
        EmailService otp= new EmailService(getOtpRequestedTime( expireTime, expireIn), getOtpnum(), getUser());




        EmailUtil.sendEmail(session, toEmail, "TLSEmail Testing Subject", message);
//        otpService.save(otp);


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

    public LocalDateTime getOtpRequestedTime(LocalDateTime expireTime,int expireIn) {

        this.otpRequestedTime = LocalDateTime.now().plusSeconds(expireIn);

        this.otpRequestedTime = expireTime;

        return otpRequestedTime;
    }

    public void setOtpRequestedTime(LocalDateTime otpRequestedTime) {
        this.otpRequestedTime = otpRequestedTime;
    }

    public @NotEmpty User getUser() {
        return user;
    }



}
