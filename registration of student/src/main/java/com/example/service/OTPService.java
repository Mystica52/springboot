package com.example.service;

import com.example.model.Otp;
import com.example.model.User;
import org.springframework.http.ResponseEntity;


public interface OTPService  {
    //Otp findByOtpnum(String otp);
   // Otp findByUser(User user);
//    void save(User user, String otp);

//    Otp findByOptnum(int optnum);

//    Otp save(EmailService otp);

    void createVerification(String email) throws Exception;

    ResponseEntity<String> verifyEmail(int otp1);
//
//
//
//    Otp createOtp();
//
//    void saveOtp(Otp otp);
//
//    Otp findByOtpnum(int otpnum);
//
//    void removeOtp(Otp otp);
//
//    void removeByOtp(int otp);
}


//public class OTPService {
//
//    private static final Integer EXPIRE_MINS = 5;
//    private final LoadingCache<String, Integer> otpCache;
//    public OTPService(){
//        super();
//        otpCache = CacheBuilder.newBuilder().
//                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
//                .build(new CacheLoader<String, Integer>() {
//
//
//                    @Override
//                    public Integer load(String key) throws Exception {
//                        return 0;
//                    }
//                });
//    }
//
////    public int generateOTP(String key){
////        Random random = new Random();
////        int otp = 100000 + random.nextInt(900000);
////        otpCache.put(key, otp);
////        return otp;
////    }
//
//    public int getOtp(String key){
//        try{
//            return (int) otpCache.get(key);
//        }catch (Exception e){
//            return 0;
//        }
//    }
//
//    public void clearOTP(String key){
//        otpCache.invalidate(key);
//    }
//}
