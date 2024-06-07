package com.example.restapiex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapiex.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

	Member findByUsername(String username);

}
