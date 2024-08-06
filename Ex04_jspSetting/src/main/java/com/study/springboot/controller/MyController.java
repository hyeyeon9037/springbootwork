package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "jsp로 실행";
	}
	
	@RequestMapping("/test1")
	// 실행하도록 만들어주는 public
	public String test1() {
		return "test1";
		//  /WEB-INF/views/test1.jsp를 찾아서 웹으로 보여준다는 말이당
		
		// 이걸 실행하게 된다면 http://localhost:8080/test1 이 된다.
	}
	
	@RequestMapping("/test2")
	public String test2() {
		return "sub/test2";
		// 호출시 /WEB-INF/views/sub/test2.jsp
		// 이걸 실행하게 된다면 http://localhost:8080/test2 이 된다.
	}
}