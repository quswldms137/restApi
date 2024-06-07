package com.example.restapiex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private Dash dash;
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("dash", dash);
		
		return "admin";
	}
}
