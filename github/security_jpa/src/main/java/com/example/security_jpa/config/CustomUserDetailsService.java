package com.example.security_jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security_jpa.entity.Member;
import com.example.security_jpa.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private MemberRepository memberRepository;
	
	@Override          //사용자를 username을 통해 불러오겠다(load) / String username이 security의 기본세팅
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//사용자정보를 디비에서 가져오는 코드
		Member member = memberRepository.findByUsername(username);
		
		if(member != null) {
			return new CustomUserDetails(member); 
		}
		return null;
	}

}
