package com.example.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restapi.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	@Query(value="SELECT * FROM member WHERE id = :id", nativeQuery=true)
	Member findByMid(Long id);
}
