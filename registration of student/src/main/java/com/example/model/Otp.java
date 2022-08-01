package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "otp")

@Data
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer OTP_ID;

    private Integer id;

    @Column(name="otpnum")
    private Integer otpnum;
    @Column(name="otp_Requested_Time")
    private Date otpRequestedTime;

    @OneToMany(fetch = FetchType.LAZY)
     @JoinColumn ( name = "id", referencedColumnName = "id")
    private Collection<User> user;




}
