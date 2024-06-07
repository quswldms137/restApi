package com.example.restapi.controller;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dto.MemberDto;

	@RestController
	public class ApiController {
		
		@CrossOrigin(origins = "*")
		@GetMapping("/api")
		public String root(@RequestParam("num")int num) {
			String msg = "";
			if(num == 1) {
				msg = "Good Morning!!";
			}else if(num == 2) {
				msg = "Good Afternoon!!";
			}else {
				msg = "Good Evening!!";
			}
			
			return msg;
		}
		
		@PostMapping("/post")
		public String save(@RequestBody MemberDto memberDto) {
			return "결과 : " + memberDto;
		}
	}