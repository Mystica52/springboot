package com.example.studentDemo;

import com.example.studentDemo.model.Student;
import com.example.studentDemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication()

public class StudentDemoApplication  {

	public static void main(String[] args) {
		SpringApplication.run(StudentDemoApplication.class, args);

	}
//	@Autowired
//	private StudentRepository studentRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Student student= new Student();
//		student.setName("rose");
//		student.setCourse("php");
//		student.setEmail("rd@gmail.com");
//		studentRepository.save(student);
//
//		Student student1= new Student();
//		student1.setName("james");
//		student1.setCourse("python");
//		student1.setEmail("jm@gmail.com");
//		studentRepository.save(student1);

	//}
}
