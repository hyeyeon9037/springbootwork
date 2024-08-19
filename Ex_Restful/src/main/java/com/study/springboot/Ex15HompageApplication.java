package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ex15HompageApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex15HompageApplication.class, args);
	}

}