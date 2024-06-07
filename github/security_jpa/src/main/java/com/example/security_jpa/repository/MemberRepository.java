package com.example.security_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security_jpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>  {

	public Member findByUsername(String username);

}
