package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA 리스너 기능 활성화
@SpringBootApplication
public class Project008Application {

	public static void main(String[] args) {
		SpringApplication.run(Project008Application.class, args);
	}

}
