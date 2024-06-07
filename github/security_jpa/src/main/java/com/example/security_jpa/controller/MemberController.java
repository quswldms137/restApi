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
@RequestMapping("/members")
public class MemberController {
	
	@RequestMapping("/")
	public String welcom(Model model, Principal principal, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		System.out.println("member welcom-----");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal(); //권한있는 사용자 정보 가져옴 
		
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("name", userDetails.getName());
		
		//Principal 객체 주입 : 사용자 인증 정보를 가진 객체 
		model.addAttribute("username2", principal.getName());  // principal.getName() : getName이지만 username(id)을 가져옴 
		
		//@AuthenticationPrincipal을 사용하여 UserDetails 인터페이스의 구현체 CustomUserDetails 객체를 주입받아 사용하기
		model.addAttribute("username3", customUserDetails.getUsername());
		model.addAttribute("name3", customUserDetails.getName());
		model.addAttribute("role3", customUserDetails.getRole());
		
		return "members/welcome";
	}
}
