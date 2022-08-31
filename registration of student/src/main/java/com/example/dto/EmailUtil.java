package com.example.dto;

import com.example.repository.UserRepository;
import com.example.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailUtil {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private static OTPService otpService;

    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */

    @Autowired
    private static JavaMailSender javaMailSender;
    public static void sendEmail(Session session, String toEmail, String subject, String body){

        try
        {
//            MimeMessage msg = javaMailSender.createMimeMessage();




            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
//            MimeMessageHelper helper = new MimeMessageHelper(msg, true);


            msg.setFrom(new InternetAddress("mdukuzeyezu@bk.rw", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("dukumystica20@gmail.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(String.valueOf(body), "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
