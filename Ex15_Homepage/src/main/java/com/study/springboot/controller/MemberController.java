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

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes({"loginUser"})
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	HttpSession session;
	// 하나의 공간이라고 생각 ㄱㄱ
	
	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/enrollForm")
	public String enrollForm() {
		return "member/enrollForm";
	}

	@GetMapping("/myPage")
	public String myPage() {
		return "member/myPage";
	}
	
	@GetMapping("/idCheck")
	@ResponseBody // ajax로 url로 보내면 꼭 써야함
	public boolean idCheck(@RequestParam("id") String id) {
		return memberService.idCheck(id);
	}
	
	@PostMapping("/memberInsert") // enrollForm action에 있음
	public String memberInsert(Member member) {
		String enPass = passwordEncoder.encode(member.getPassword());
		// encode : 코드를 바꿔준다는소리
		member.setPassword(enPass);
		memberService.memberInsert(member);
		return "redirect:/";
	}
	/*
	 return "redirect:/";은
	 
	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	로 가라는 소리임
	*/
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Optional<Member> loginUser = memberService.login(member);
		if(loginUser.isPresent()) {
			// isPresent()는 Optional 객체에 값이 존재하는지 여부를 확인하는 메소드
			// 값이 존재하는 경우: isPresent()는 true를 반환, 값이 없는 경우: isPresent()는 false를 반환
			Member m = loginUser.get();
			if(passwordEncoder.matches(member.getPassword(), m.getPassword())) {
				// passwordEncoder.matches : 사용자가 입력한 비밀번호와 저장된 암호화된 비밀번호가 일치하는지 확인
				model.addAttribute("loginUser", m);
				// 세션으로 'loginUser'라는 이름으로 사용자 객체를 전달
	            // 모델에 'loginUser' 속성을 추가하여 뷰에서 사용할 수 있도록 함
				
				// 원래 requestScope => sessionScope로 바꾸기							
				// 클래스에 @SessionAttributes({"loginUser"})어노테이션 달기
			}
		}
		
		//댓글
		
		
		String url = (String)session.getAttribute("boardDetailUrl");
		if(url == null) {
			url = "/";
		}
		return "redirect:" + url;
	}
	
	/*
	 * @SessionAttributes + model을 통해 로그인정보를 관리하는 경우
	     SessionStatus객체를 통해 사용완료 처리해야 한다.
	     - session객체를 폐기하지 않고 재사용 
	 */
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		if(!status.isComplete()) 
			// isComplete() : 세션이 완료된 상태인지 확인합니다.
			// 반환값 → true: 세션이 완료된 상태, false: 세션이 아직 완료되지 않은 상태
			status.setComplete();
		// setComplete() : 세션을 완료된 상태로 설정 <로그아웃 시 세션을 무효화할 때 사용>
		return "redirect:/";
	}
}

/*
@GetMapping: HTTP GET 요청을 처리합니다.
@PostMapping: HTTP POST 요청을 처리합니다.
@PutMapping: HTTP PUT 요청을 처리합니다.
@DeleteMapping: HTTP DELETE 요청을 처리합니다.
@PatchMapping: HTTP PATCH 요청을 처리합니다. 

Optional은 값이 있거나 없을 수 있는 상황을 처리하는 자바 클래스입니다. 
값을 감싸는 컨테이너처럼 생각하면 됩니다.

값이 있으면: Optional에 담겨 있는 값을 사용할 수 있습니다.
값이 없으면: 빈 상태로 존재합니다.
핵심 메소드
isPresent(): 값이 있는지 확인
ifPresent(Consumer): 값이 있을 때만 특정 작업 수행
orElse(T other): 값이 없으면 기본값을 반환
 */
