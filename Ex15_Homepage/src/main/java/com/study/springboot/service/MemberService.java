package com.study.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Member;
import com.study.springboot.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;

	public boolean idCheck(String id) {
		// existsById() : id가 존재하는지 DB검색 하여 반환결과 true or false 반환
		return memberRepository.existsById(id);
	}

	public Member memberInsert(Member member) {
		return memberRepository.save(member);
		// save : 값을 업데이트해줌, 값이없을땐 생성까지 도와줌
	}

	public Optional<Member> login(Member member) {
		return memberRepository.findById(member.getId());	
	}
}

/*
@Service는 클래스를 스프링 서비스 컴포넌트로 등록하여, 비즈니스 로직을 처리하는 역할을 담당합니다.
@Autowired는 의존성 주입을 통해 스프링 컨테이너에서 관리하는 Bean을 자동으로 주입하여, 객체 간의 의존성을 관리합니다.
*/
