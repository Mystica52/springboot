package com.example;

import com.example.model.Role;
import com.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//		(scanBasePackages={"com.example.service.OTPService","com.example.service.EmailTemplate"})
@EntityScan("com.example.model")


public class GoodDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoodDemoApplication.class, args);
	}


}