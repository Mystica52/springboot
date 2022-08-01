package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//		(scanBasePackages={"com.example.service.OTPService","com.example.dto.EmailTemplate"})
@EntityScan("com.example.model")


public class GoodDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoodDemoApplication.class, args);
	}

}
