package com.example.viewServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	@RequestMapping("/main")
	public String main() {
		return "member/main";
	}
	//게시물 등록(등록폼, 등록처리) 
	@RequestMapping("/regBoard")
	public String regBoard() {
		return "/member/regBoardForm";
	}

}
