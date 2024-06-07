package com.example.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.news.entity.Member;
import com.example.news.entity.Newspaper;
import com.example.news.repository.MemberRepository;
import com.example.news.repository.NewspaperRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	NewspaperRepository newspaperRepository;
	
	@RequestMapping("/")
	public String root() {
		return "redirect:main";
	}
	
	@RequestMapping("/main")
	public String main(HttpServletRequest req) {
		return "main";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	@RequestMapping("/login")
	public String login(@RequestParam("name") String name, HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		Member member = memberRepository.findByName(name);
		if(member != null) {
			session.setAttribute("name", name);
			System.out.println("로그인 성공");
			return "redirect:main";
		}else {
			System.out.println("로그인 실패");
			return "loginForm";
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:main";
	}
	
	@RequestMapping("/articlewriteForm")
	public String articlewriteForm() {
		return "articlewriteForm";
	}
	
	@RequestMapping("/mypage")
	public String mypage(Model model) {
		List<Newspaper> news = newspaperRepository.findAll();
		model.addAttribute("news", news);
		return "mypage";
	}
}	
