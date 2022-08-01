package com.example.service;

import com.example.dto.EmailTemplate;
import com.example.dto.UserRegistrationDto;
import com.example.model.Otp;
import com.example.model.User;
import com.example.repository.OtpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



public interface OTPService  {
     Otp findByOptnum(int optnum);

    Otp save(EmailTemplate otp);

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
