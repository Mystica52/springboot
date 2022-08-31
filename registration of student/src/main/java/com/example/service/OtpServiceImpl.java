package com.example.service;

import com.example.model.Otp;
import com.example.model.User;
import com.example.repository.OtpRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OtpServiceImpl implements OTPService{

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public void createVerification(String email) throws Exception {
        List<User> users = (List<User>) userRepository.findByEmail(email);
        User user;
        if (users.isEmpty()) {
            user = new User();
            user.setEmail(email);
            userRepository.save(user);
        } else {
            user = users.get(0);
        }

        List<Otp> otps = (List<Otp>) otpRepository.findByUserEmail(email);
        Otp otp;
        if (otps.isEmpty()) {
            otp = new Otp();
            otp.setUser(user);
            otpRepository.save(otp);
        } else {
            otp =otps.get(0);
        }

        emailService.sendEmail();
    }

    @Override
    public ResponseEntity<String> verifyEmail(int otp1){
        List<Otp> otps = (List<Otp>) otpRepository.findByOtpnum(otp1);
        if(otps.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }

        Otp otp = otps.get(0);
        if (otp.getOtpRequestedTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.unprocessableEntity().body("Expired token.");
        }



        otp.setConfirmedDateTime(LocalDateTime.now());
        otp.setStatus(Otp.STATUS_VERIFIED);
        otp.getUser().setIsActive(true);
        otpRepository.save(otp);

        return ResponseEntity.ok("You have successfully verified your email address.");
    }

//    @Autowired
//    private OtpRepository Repository;
//    @Override
//    public Otp findByOptnum(int optnum) {
//        return Repository.findByOtpnum(optnum);
//    }
//        private Timestamp calculateExpiryDate(int expiryTimeMinutes){
//        Calendar cal= Calendar.getInstance();
//        cal.add(Calendar.MINUTE,expiryTimeMinutes);
//        return new Timestamp(cal.getTime().getTime());
//    }
//
//
//
//    @Autowired
//    private OtpRepository repository ;
//    public  Otp save(EmailService otp) {
//        Otp user = new Otp();
//        user.setOtpnum(otp.getOtpnum());
//        user.setOtpRequestedTime(calculateExpiryDate(24*60));
//        user.setUser(otp.getUser());
//        return repository.save(user);
//    }
//

}
//package com.example.service;
//
//import com.example.model.Otp;
//import com.example.model.User;
//import com.example.repository.OtpRepository;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.crypto.keygen.BytesKeyGenerator;
//import org.springframework.security.crypto.keygen.KeyGenerators;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.nio.charset.Charset;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Calendar;
//import java.util.Random;
//
//
//@Service
//public class OtpServiceImpl implements OTPService{
//
//    @Autowired
//    private OtpRepository Repository;
//
//
//    @Autowired
//    private OtpRepository repository ;
////    public  Otp save(EmailService otp) {
////        Otp user = new Otp();
////        user.setOtpnum(otp.getOtpnum());
////        user.setOtpRequestedTime(otp.getOtpRequestedTime(LocalDateTime.now(),5));
////        user.setUser(otp.getUser());
////        return repository.save(user);
////    }
////
////    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
////    private static final Charset US_ASCII = Charset.forName("US-ASCII");
////    @Value("$jdj.secure.token.validity")
////    private int expireIn;
//
//
////    @Override
////    public Otp createOtp(){
////        int tokenValue = Integer.parseInt(new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII)); // this is a sample, you can adapt as per your security need
////        Otp otp1=new Otp();
////        otp1.setOtpnum(String.valueOf(tokenValue));
////        otp1.setOtpRequestedTime(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));
////        this.saveOtp(otp1);
////        return otp1;
////
////
////    }
////
////    @Override
////    public void saveOtp(Otp otp) { repository.save(otp);
////    }
//
//    @Transactional
//    public Otp findByOtpnum(String otp){
//        return repository.findByOtpnum(otp);
//    }
//
//    @Transactional
//    public Otp findByUser(User user){
//        return repository.findByUser(user);
//
//
//    }
//
//    @Transactional
//    public void save(User user, String otp){
//        Otp otp1= new Otp(otp,user);
//        //set expiry date to 24 hrs
//        otp1.setExpiryDate(calculateExpiryDate(24*60));
//        repository.save(otp1);
//    }
//
//    private Timestamp calculateExpiryDate(int expiryTimeMinutes){
//        Calendar cal= Calendar.getInstance();
//        cal.add(Calendar.MINUTE,expiryTimeMinutes);
//        return new Timestamp(cal.getTime().getTime());
//    }
////    @Override
////    public  void removeOtp(Otp otp){
////        repository.delete(otp);
////    }
////
////    @Override
////    public  void removeByOtp(int otp){
////        repository.removedByOtp(otp);
////    }
////    public int getTokenValidityInSeconds() {
////        return expireIn;
////    }
//
//
//}
