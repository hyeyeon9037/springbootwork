package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.repository.MemverRepository;

@Service
public class MemberService {
	
	@Autowired
	MemverRepository memberRepository;

	public boolean idCheck(String id) {
		// id가 존재하는지 DB검색 하여 반환결과 true or false 반환
		return memberRepository.existsById(id) ;
	}
	
}
