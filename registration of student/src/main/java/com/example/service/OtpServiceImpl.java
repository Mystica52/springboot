package com.example.service;

import com.example.model.Otp;
import com.example.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OtpServiceImpl implements OTPService{

    @Autowired
    private OtpRepository Repository;
    @Override
    public Otp findByOptnum(int optnum) {
        return Repository.findByOtpnum(optnum);
    }



    @Autowired
    private OtpRepository repository ;
    public  Otp save(EmailService otp) {
        Otp user = new Otp();
        user.setOtpnum(otp.getOtpnum());
        user.setOtpRequestedTime(otp.getOtpRequestedTime());
        return repository.save(user);
    }


}
