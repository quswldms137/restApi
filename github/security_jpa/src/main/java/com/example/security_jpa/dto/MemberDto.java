package com.example.security_jpa.dto;

import lombok.Data;

@Data
public class MemberDto {
	private Integer mno;
	private String username;
	private String password;
	private String name;
	private String role;
}
