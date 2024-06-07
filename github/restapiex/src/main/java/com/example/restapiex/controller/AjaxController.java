package com.example.restapiex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapiex.dto.LoginDto;
import com.example.restapiex.entity.Member;
import com.example.restapiex.repository.MemberRepository;

@RestController
@RequestMapping("/api")
public class AjaxController {
	@Autowired
	MemberRepository memberRepository; //아이디중복체크 
	
	private Dash dash;
	private int cnt = 0;
	private int cnt2 = 0;
	
	//생성자 주입 
	public AjaxController(Dash dash) {
		this.dash = dash;
	}
	
	
	//폼 데이터 처리 메서드
	//@PostMapping("/submitForm")  
	public String submitForm(@RequestParam Map<String, String> formData) { //요청 파라미터를 맵에 저장
		//폼데이터 처리 로직
		String username = formData.get("username");
		String password = formData.get("password");
		return username + ", "+password;
	}
	@PostMapping("/submitForm")  //일반변수 사용 
	public String submitForm2(@RequestParam("username") String username, @RequestParam("password") String password) { 
		cnt++;
		dash.setC1(cnt);
		
		return username + " / "+password;
	}
	
	//@PostMapping("/submitJson")
	public String submitJson(@RequestBody Map<String, String> jsonData) {
		//json 데이터 처리 로직
		String username = jsonData.get("username");
		String password = jsonData.get("password");
		
		return "json submitted successfully:" + username ;
	}
	@PostMapping("/submitJson") //logindto 사용 
	public String submitJson2(@RequestBody LoginDto loginDto) {
		//json 데이터 처리 로직
		String username = loginDto.getUsername();
		String password = loginDto.getPassword();
		
		cnt2++;
		dash.setC2(cnt2);
		
		return "json submitted successfully: " + username + " / " + password;
	}
	
	//아이디 중복체크
	@GetMapping("/check")
	public String check(@RequestParam("username") String username) {
		Member member = memberRepository.findByUsername(username);
		String msg = "";
		if(member != null) {
			msg = "이미 있는 아이디입니다.";
		}else {
			msg = "사용가능합니다.";
		}
		return msg; 
	}
}
