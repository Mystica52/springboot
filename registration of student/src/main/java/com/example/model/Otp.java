package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "otp")

@Data
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Otp {

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_VERIFIED = "VERIFIED";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer OTP_ID;

    @Column(name="otpnum")
    private Integer otpnum;

    @Column(name = "otp_requested_time")
    private LocalDateTime otpRequestedTime;

    private LocalDateTime confirmedDateTime;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn ( name = "user_id")
    private User user;

    public Otp(Integer otpnum, int expireIn) {

        this.otpnum = otpnum;
        this.otpRequestedTime = LocalDateTime.now().plusSeconds(expireIn);
    }

//    public Otp(Integer otpnum, t expireTime) {
//
//        this.otpnum = otpnum;
//        this.otpRequestedTime = expireTime;
//    }
//
//    public boolean isExpire() {
//
//        return LocalDateTime.now().isAfter(otpRequestedTime);
//    }




}