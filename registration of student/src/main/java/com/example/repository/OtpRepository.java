package com.example.repository;

import com.example.model.Otp;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepository extends JpaRepository<Otp,Long> {
    Otp findByOtpnum(int optnum);
//    Long removedByOtp(int otp);
    Otp findByUser(User user);

    Otp findByUserEmail(String email);
}


