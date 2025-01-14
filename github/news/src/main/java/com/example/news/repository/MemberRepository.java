package com.example.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.news.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	Member findByName(String name);
	
	//@Query(value="SELECT * FROM newspaper n JOIN member m ON n.writer = m.name WHERE writer = :writer ",nativeQuery = true)
	//Member findByName(Member writer);

}
