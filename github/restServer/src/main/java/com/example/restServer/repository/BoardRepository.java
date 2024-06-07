package com.example.restServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restServer.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
