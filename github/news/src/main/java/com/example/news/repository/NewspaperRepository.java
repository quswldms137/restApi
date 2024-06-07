package com.example.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.news.entity.Newspaper;

public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {


}
