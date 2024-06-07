package com.example.security_jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	      // BCryptPasswordEncoder : 패스워드 암호화해주는 객체
	@Bean //@Bean : 메서드의 반환타입을 빈으로 등록해서 사용가능 
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//인가
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests((auth) -> auth   //람다표현식(함수형) 
				.requestMatchers("/", "/registForm", "/registProc").permitAll() //요청경로("","","")에 대해서는 전부 허용하겠다 
				.requestMatchers("/members/**").hasAnyRole("ADMIN", "MEMBER") // /members 폴더 이하 모든것 , (ROLE_)"ADMIN"
				.requestMatchers("/admin").hasRole("ADMIN")
				.anyRequest().permitAll()
				
				//.anyRequest().authenticated() //그외 나머지는 다 인증을 거쳐야한다 라는 뜻 
				
				);
		http.formLogin((auth) -> auth   //폼로그인 쓸거고 , 다 허용하겠다 라는 뜻 
				.loginPage("/login") //로그인 폼 지정 - 쓰지 않으면 Spring Security가 제공하는 로그인폼 사용/ 직접 만든 로그인페이지를 사용하겠다 라는 뜻 
				.loginProcessingUrl("/loginProc") //로그인 폼 지정 후 폼 파라미터 보내는 경로지정 - 컨트롤러에 직접 만들지 않는다(Spring Security가 알아서 처리함)
				.defaultSuccessUrl("/success") //로그인 성공하면 success경로로 보내겠다라는 뜻 
				.permitAll()
				);
		
		http.csrf(AbstractHttpConfigurer::disable); //csrf 기능 끄기
		return http.build();
	}
}
