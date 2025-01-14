package com.example.restapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dto.MemberDto;
import com.example.restapi.entity.Member;
import com.example.restapi.repository.MemberRepository;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {
	@Autowired
	MemberRepository memberRepository;
	
	//http://localhost:8085/api/v1/post-api/domain
	//@RequestMapping(value = "/domain", method=RequestMethod.POST)
	@PostMapping("/domain")
	public String postExample() {
		return "hello postapi";
	}
	
	//http://localhost:8085/api/v1/post-api/member0
	@PostMapping("/member0")
	public String postMember0(@RequestParam("name") String name, 
			@RequestParam("email") String email, @RequestParam("addr") String addr ) {
		return name + ", " + email + ", " + addr;
	}
	
	//Content-Type : application/json
	//http://localhost:8085/api/v1/post-api/member
	@PostMapping("/member")
	public String postMember(@RequestBody Map<String, Object> postData) {
		StringBuilder sb = new StringBuilder();
		postData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	//text/plain;charset=UTF-8
	//http://localhost:8085/api/v1/post-api/member2
	@PostMapping("/member2")
	public String postMemberDto(@RequestBody MemberDto memberDto) {
		return memberDto.toString();
	}
	
	//application/json
	//http://localhost:8085/api/v1/post-api/member3
	@PostMapping("/member3")
	public MemberDto postMemberDto2(@RequestBody MemberDto memberDto) {
		return memberDto;
	}
	
	//등록
	//http://localhost:8085/api/v1/post-api/regist
	@PostMapping("/regist")
	public String postRegist(@RequestParam("name") String name, 
			@RequestParam("email") String email, @RequestParam("addr") String addr) {
		Member member = new Member();
		member.setName(name);
		member.setEmail(email);
		member.setAddr(addr);
		memberRepository.save(member);
		return name+", "+email+", "+addr;
	}
	
	//수정
	//http://localhost:8085/api/v1/post-api/update
	@PostMapping("/update")
	public String postUpdate(@RequestBody MemberDto memberDto) {
		Member member = memberRepository.findByMid(memberDto.getId());
		member.setName(memberDto.getName());
		member.setEmail(memberDto.getEmail());
		member.setAddr(memberDto.getAddr());
		memberRepository.save(member);
		
		return memberDto.getName()+", "+memberDto.getEmail()+", "+memberDto.getAddr();
	}
}
