package com.example.restapi.dto;

import lombok.Data;

@Data
public class MemberDto {  //dto(data transfer object) : 계층(레이어드 아키텍처)간 데이터 교환(전송)에 사용되는 객체
	private Long id;		  							// 프리젠테이션-비즈니스-데이터엑세스(dao)
	private String name;
	private String email;
	private String addr;
	
}