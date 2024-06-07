package com.example.security_jpa.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security_jpa.config.CustomUserDetails;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/")
	public String welcome(Model model, Principal principal, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		System.out.println("admin welcome-------");
		//1.Security Context로부터 얻어낸 UserDetails 객체(사용자정보 객체(Member)를 품은 객체)를 Model로 받아보기>
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//UserDetails userDetails = (UserDetails) authentication.getPrincipal();  << UserDetails 라는 객체는 원래 있는거
		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal(); // <<CustomUserDetails 는 내가 만든 클래스에서 게터 이용해서 데이터 가져오기
		
		model.addAttribute("username", userDetails.getUsername()); //UserDetails 에는 getUsername(), getPassword() 만 존재(로그인할때 필요한 정보)
		model.addAttribute("name", userDetails.getName()); //CustomUserDetails 클래스를 만들어서 Member 정보 가져오기
		
		//2.Principal 객체 값을 Model로 받기
		model.addAttribute("username2", principal.getName()); 
		
		//3.@AthenticationPrincipal을 사용하여 UserDetails의 구현체(CustomUserDetails 객체)를 파라미터 주입받아 사용하기
		model.addAttribute("username3", customUserDetails.getUsername());
		model.addAttribute("name3", customUserDetails.getName());
		model.addAttribute("role3", customUserDetails.getRole());
		
		return "admin/welcome";
	}
}
