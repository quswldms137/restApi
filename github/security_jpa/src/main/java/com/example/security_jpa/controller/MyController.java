package com.example.security_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.security_jpa.dto.MemberDto;
import com.example.security_jpa.entity.Member;
import com.example.security_jpa.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyController {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/")
	public String root() {
		log.info("root-----------");
		return "index";
	}
	//회원가입 
	@RequestMapping("/registForm")
	public String registForm() {
		log.info("registForm--------");
		return "registForm";
	}
	@RequestMapping("/registProc")    
	public @ResponseBody String registProc(MemberDto memberDto) {
		log.info("registProc----"+memberDto);
		Member member = new Member();
		member.setUsername(memberDto.getUsername());
		
		//security 관련된 설정파일 만들기 -> for.암호화(기존패스워드);위해 -> bCryptPasswordEncoder
		String newPw = bCryptPasswordEncoder.encode(memberDto.getPassword());   
		member.setPassword(newPw); //암호화된 패스워드 : setPassword(newPw);
		
		member.setName(memberDto.getName());
		member.setRole("ROLE_MEMBER");
		
		memberRepository.save(member); //save(엔티티);
		return "회원가입 완료!";
	}
	
	//로그인
	@RequestMapping("/login")
	public String loginForm() {
		log.info("login--------");
		return "login";
	}
	
	@RequestMapping("/success")
	public @ResponseBody String success() {
		return "로그인성공!";
	}
	
	
}
