package com.example.restapiex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public String root() {
		return "redirect:/index2";
	}
	
	@RequestMapping("/ajaxex")
	public String ajaxes() {
		
		return "ajaxex";
	}
	
	@RequestMapping("/index2")
	public String memo() {
		return "index2";
	}
	@RequestMapping("/index3")
	public String memo2() {
		return "index3";
	}
}
