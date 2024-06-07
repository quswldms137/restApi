package com.example.security_jpa.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.security_jpa.entity.Member;

public class CustomUserDetails implements UserDetails {
	private Member member;
	
	//생성자주입 , 데이터 할당 
	public CustomUserDetails(Member member) {
		this.member = member;
	}
	//** Collection : SET, MAP, ArrayList...
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return member.getRole();
			}
		});
		return collection; //기본세팅 : null / null이면 안됨  
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}
	//
	public String getName() {
		return member.getName();
	}
	public String getRole() {
		return member.getRole();
	}
	
}	
