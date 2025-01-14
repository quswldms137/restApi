package com.example.restapiex.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapiex.dto.MemoDto;
import com.example.restapiex.entity.Memo;
import com.example.restapiex.repository.MemoRepository;

@RestController
@RequestMapping("/memoapp")
public class MemoController {
	@Autowired
	private MemoRepository memoRepository;
	
	
	@RequestMapping("/main")
	public String root() {
		return "hello memo";
	}
	
	//GET 메소드 (서버에 자료요청할 때 - 어떤 자료인지 식별할 수 있는 파라미터가 있어야겠다)
	//http://localhost:8086/memoapp/memo?mno=1
	@GetMapping("/memo")
	public Memo getMemo(@RequestParam("mno") Integer mno) {
		System.out.println("getMemo---- mno : "+mno);
		
		Optional<Memo> result = memoRepository.findById(mno);
		Memo memo = result.get();
		
		return memo;
	}
	//index2.html / jQuery 사용 / GET 조회 
	//@GetMapping("/memo")
	public String getMemo2(@RequestParam("mno") Integer mno) {
		Memo memo = memoRepository.findByMno(mno);
		return memo.toString();
	}
	
	
	//POST 메소드(서버에 자료 새로 등록할 때 - ajax로 json 데이터가 파라미터로 넘어오겠다 >> @requestBody )
	//http://localhost:8086/memoapp/memo
	//{ "title" : "test title", "content" : "test content", "writer" : "hgd"}
	//@PostMapping("/memo")  //	text/plain;charset=UTF-8
	public String postMemo(@RequestBody MemoDto memoDto) {
		System.out.println("postMemo---- memoDto : "+memoDto);
		return "postMemo---- memoDto : "+memoDto;  
	}
	@PostMapping("/memo")  //	application/json
	public Memo postMemo2(@RequestBody MemoDto memoDto) {
		System.out.println("postMemo---- memoDto : "+memoDto);
		
		Memo memo = new Memo();
		memo.setTitle(memoDto.getTitle());
		memo.setContent(memoDto.getContent());
		memo.setWriter(memoDto.getWriter());
		
		Memo result = memoRepository.save(memo);
		
		return result;
	}
	//index2.html / jQuery 사용 / POST 등록 
	//@PostMapping("/memo")
	public String postMemo3(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("writer") String writer) {
		Memo memo = new Memo();
		memo.setTitle(title);
		memo.setContent(content);
		memo.setWriter(writer);
		
		memoRepository.save(memo);
		return title + " / " + content + " / " + writer;
	}
	
	//PUT 메소드(서버에 자료 재등록할 때)
	//http://localhost:8086/memoapp/memo
	//{ "title" : "test title", "content" : "test content", "writer" : "hgd"}
	@PutMapping("/memo")  
	public String putMemo(@RequestBody MemoDto memoDto) {  // body를 json으로 보내주니까 @RequestBody MemoDto 객체로 변환 
		System.out.println("putMemo---- memoDto : "+memoDto);
		
		Memo memo = memoRepository.findByMno(memoDto.getMno());
		//Memo memo = new Memo();
		memo.setMno(memoDto.getMno());
		memo.setTitle(memoDto.getTitle());
		memo.setContent(memoDto.getContent());
		memo.setWriter(memoDto.getWriter());
		
		Memo result = memoRepository.save(memo);
		
		return result.toString();
	}
	//PUT : @RequestParam() 이욯해서 수정하기 
	//@PutMapping("/memo")
	public String putMemo2(@RequestParam("mno") Integer mno, @RequestParam("title") String title, 
			@RequestParam("content") String content, @RequestParam("writer") String writer) {
		
		Memo memo = memoRepository.findByMno(mno);
		System.out.println(mno);
		memo.setMno(mno);
		memo.setTitle(title);
		memo.setContent(content);
		memo.setWriter(writer);
		
		Memo result = memoRepository.save(memo);
		
		return result.toString();
	}
	//index2.html / jQuery 사용 / PUT 수정
	//@PutMapping("/memo")
	public String putMemo3(@RequestParam("mno") Integer mno, @RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("writer") String writer) {
		Memo memo = new Memo();
		memo.setMno(mno);
		memo.setTitle(title);
		memo.setContent(content);
		memo.setWriter(writer);
			
		Memo result = memoRepository.save(memo);
		return result.toString();
	}
	
	//DELETE 메소드(서버의 자료 삭제할 때 - 삭제할 자료를 식별할 수 있는 파라미터가 넘어오겠다)
	//http://localhost:8086/memoapp/memo/1
	@DeleteMapping("/memo/{mno}")
	public String deleteMemo(@PathVariable("mno") Integer mno) {
		System.out.println("deleteMemo---- mno : "+mno);
		
		memoRepository.deleteById(mno);
		
		return "deleteMemo---- mno : "+mno;
	}
}
