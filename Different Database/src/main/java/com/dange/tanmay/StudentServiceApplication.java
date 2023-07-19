package com.dange.tanmay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dange.tanmay"})
public class StudentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
}

