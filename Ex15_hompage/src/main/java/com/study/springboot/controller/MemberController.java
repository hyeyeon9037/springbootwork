package com.study.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;

@Controller
@SessionAttributes({"loginUser"}) // sessionScope로 받겠다는 소리 60번줄 참고 - 
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
      String enPass = passwordEncoder.encode(member.getPassword()); //  passwordEncoder.encode()는 암호화 해주는 것!
      member.setPassword(enPass); // 암호화된 패스워드를 얻어와서 member한테 ㄱㄱ
      memberService.memberInsert(member);
      return "redirect:/";
   }
   
   @PostMapping("/login")
   public String login(Member member, Model model) {
       Optional<Member> loginUser = memberService.login(member);
       if(loginUser.isPresent()) {
          Member m = loginUser.get();
          if(passwordEncoder.matches(member.getPassword(), m.getPassword())) {
        	//참이면 패스워드가 맞음, 거짓이면 맞지않은 것
             model.addAttribute("loginUser", m);
             // requestScope => sessionScope로 바꾸기
             // 클래스에 @SessionAttributes({"loginUser"}) 어노테이션 달기
          }
       }else {
          
       }
      return "redirect:/";
   }
   
   /*
   	 @SessionAttributes + model을 통해 로그인 정보를 관리하는 경우
   	   SessionStatus객체를 통해 사용완료 처리해야 한다.
   	   - session객체를 폐기하지 않고 재사용
   */
   
   @GetMapping("/logout")
   public String logout(SessionStatus status) {
	   if(!status.isComplete()) // @SessionAttributes + model을 통해 로그인 정보를 관리하는 경우
		   status.setComplete();
	   return "redirect:/";
   }
   
   
   
   
   
  
   
}