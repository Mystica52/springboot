package com.example.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "otp")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    @Column(name="otpnum")
    private Integer otpnum;

    @Column(name="otp_Requested_Time")
    private Date otpRequestedTime;
    public int getOtpnum() {
        return this.otpnum;
    }

    public void setOtpnum(int i) {
        this.otpnum = i ;
    }

    public Date getOtpRequestedTime() {
        return otpRequestedTime;
    }

    public void setOtpRequestedTime(Date otpRequestedTime) {
        this.otpRequestedTime = otpRequestedTime;
    }


}
