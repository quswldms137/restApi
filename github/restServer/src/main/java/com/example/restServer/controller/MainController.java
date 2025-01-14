package com.example.restServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restServer.entity.Member;
import com.example.restServer.repository.BoardRepository;
import com.example.restServer.repository.MemberRepository;

@CrossOrigin("*")
@RestController
public class MainController {
	@Autowired
	private MemberRepository memberRepository;
	
	/*
	@RequestMapping("/")
	public String root() {
		System.out.println("root-----");
		return "index";
	}
	
	@RequestMapping("/join")
	public String join() {
		return "joinForm";
	}
	*/
	@PostMapping("/joinProc")
	public String joinProc(Member member) { //(파라미터 설정)
		member.setRole("ROLE_MEMBER");
		
		//DB에 저장
		Member result = memberRepository.save(member);
		
		if(result != null) {
			return "등록성공";
		}else {
			return "등록실패";
		}
	}
}
