package com.example.viewServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//초기설정 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean //메서드 반환자료가 빈으로 등록된다 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(auth -> auth.disable());
		
		http.httpBasic(auth -> auth.disable());
		
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		
		return http.build();
	}
}

