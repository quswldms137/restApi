package com.example.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.dto.NewsDto;
import com.example.news.entity.Member;
import com.example.news.entity.Newspaper;
import com.example.news.repository.MemberRepository;
import com.example.news.repository.NewspaperRepository;

@RestController
@RequestMapping("/news")
public class NewsController {
	@Autowired
	NewspaperRepository newspaperRepository;
	@Autowired
	MemberRepository memberRepository;
	
	//기사작성 post
	@PostMapping("/article")
	public Newspaper articleWrite2(@RequestBody NewsDto newsDto) {
		Newspaper news = new Newspaper();
		news.setTitle(newsDto.getTitle());
		news.setContent(newsDto.getContent());
		// 기사 작성자 정보를 데이터베이스에서 가져와서 설정
		Member writer = memberRepository.findByName(newsDto.getWriter());
		news.setWriter(writer);
		Newspaper result =  newspaperRepository.save(news);
		return news;
	}
	
	//기사조회 get
	@GetMapping("/article")
	public String getArticle() {
		
		return "";
	}
}
