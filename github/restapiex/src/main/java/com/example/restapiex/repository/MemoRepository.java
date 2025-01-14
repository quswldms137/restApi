package com.example.restapiex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapiex.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Integer> {

	Memo findByMno(Integer mno);

}
