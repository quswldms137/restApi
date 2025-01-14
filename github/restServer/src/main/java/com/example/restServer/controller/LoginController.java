package com.example.restServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restServer.entity.Member;
import com.example.restServer.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
public class LoginController {
	@Autowired
	MemberRepository memberRepository;
	/*
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}*/
	
	@PostMapping("/loginProc")
	public String loginfProc(@RequestParam("username") String username, 
			@RequestParam("password") String password, HttpServletRequest req) { //(파라미터 처리)
		HttpSession session = req.getSession();
		//DB에서 아이디, 비밀번호 있는지 확인
		Member member = memberRepository.findByUsernameAndPassword(username, password);
		if(member != null) {
			session.setAttribute("username", username);
			return "로그인성공";
		}else {
			return "로그인실패";
		}
	}
}
