package com.example.repository;

import com.example.model.Otp;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer > {
    Otp findByOtpnum(int optnum);
}


