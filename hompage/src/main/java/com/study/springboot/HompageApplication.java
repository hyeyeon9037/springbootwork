package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class HompageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HompageApplication.class, args);
	}
	
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
}
