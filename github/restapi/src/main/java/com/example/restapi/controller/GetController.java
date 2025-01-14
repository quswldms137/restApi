package com.example.restapi.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.restapi.dto.MemberDto;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {
	//매개변수 x
	//http://localhost:8085/api/v1/get-api/hello
	@GetMapping("/hello")
	public String getHello() {
		return "hello restapi";
	}
	
	//http://localhost:8085/api/v1/get-api/name
	@GetMapping("/name")
	public String getName() {
		return "HongGilDong";
	}
	
	//매개변수 o
	//http://localhost:8085/api/v1/get-api/variable1/123
	@GetMapping("/variable1/{variable}")
	public String getVariable1(@PathVariable("variable") String variable) {
		return variable;
	}
	
	@GetMapping("/variable2/{variable}")
	public String getVariable2(@PathVariable("variable") String var) {
		return var;
	}
	
	//쿼리스트링
	//http://localhost:8085/api/v1/get-api/request?name=홍길동&email=hgd@naver.com&addr=부산
	@GetMapping("/request")
	public String getRequestParam1(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("addr") String addr
			) {
		return name+", "+email+", "+addr;
	}
	
	//MAP 
	//http://localhost:8085/api/v1/get-api/request2?name=임꺽정&email=igj@naver.com&addr=부산
	@GetMapping("/request2")
	public String getRequestParam2(@RequestParam Map<String, String> param) {
		StringBuilder sb = new StringBuilder();
		param.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		return sb.toString();
	}
	
	//dto
	//http://localhost:8085/api/v1/get-api/request3?name=임꺽정&email=igj@naver.com&addr=부산
	@GetMapping("/request3")
	public String getRequestParam3(MemberDto memberDto) {  // << 커멘드 객체 사용 
		return memberDto.toString(); //MemberDto(name=임꺽정, email=igj@naver.com, addr=부산)
	}
	
	@GetMapping("/index")
	public MemberDto root2(MemberDto memberDto) {
		return memberDto;
	}
	
	//@GetMapping("/index")
	public ModelAndView root() {
		return new ModelAndView("index");
	}
	
}
