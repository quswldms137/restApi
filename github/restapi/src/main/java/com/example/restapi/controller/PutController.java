package com.example.restapi.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dto.MemberDto;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
	//http://localhost:8085/api/v1/put-api/member
	@PutMapping("/member")
	public String putMember(@RequestBody Map<String, Object> putData) {
		StringBuilder sb = new StringBuilder();
		putData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		return sb.toString();
	}
	
	//http://localhost:8085/api/v1/put-api/member2
	@PutMapping("/member2")
	public String putMemberDto2(@RequestBody MemberDto memberDto) {
		return memberDto.toString();
	}
	
	//http://localhost:8085/api/v1/put-api/member3
	@PutMapping("/member3")
	public MemberDto putMemberDto3(@RequestBody MemberDto memberDto) {
		return memberDto;
	}
	
	//ResponseEntity 
	//http://localhost:8085/api/v1/put-api/member4
	@PutMapping("/member4")
	public ResponseEntity<MemberDto> putMemberDto4(@RequestBody MemberDto memberDto){
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)  //응답코드 202
				.body(memberDto);
	}
}
