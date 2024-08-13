package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;

@Controller
public class MemberController {
   
   @Autowired
   MemberService memberService;
   
   @Autowired
   PasswordEncoder passwordEncoder;
   
   @RequestMapping("/")
   public String root() {
      return "index";
   }
   
   @GetMapping("/enrollForm")
   public String enrollForm() {
      return "member/enrollForm";
   }
   
   @GetMapping("/idCheck")
   /*@ResponseBody 여기다가 해줘도 됨*/
   public @ResponseBody boolean idCheck(@RequestParam("id") String id) {
      return memberService.idCheck(id);
   }
   
   @PostMapping("/memberInsert")
   public String memberInsert(Member member) {
	  passwordEncoder.encode(member.getPassword()); //  passwordEncoder.encode()는 암호화 해주는 것!
      memberService.memberInsert(member);
      return "redirect:/";
   }
}