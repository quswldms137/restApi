package com.example.restServer.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restServer.dto.BoardDto;
import com.example.restServer.entity.Board;
import com.example.restServer.entity.Member;
import com.example.restServer.repository.BoardRepository;
import com.example.restServer.repository.MemberRepository;

import net.coobird.thumbnailator.Thumbnails;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
	@Value("${spring.servlet.multipart.location}") //변수설정 
	private String uploadPath; //저장되는 경로 
	
	@Autowired
	MemberRepository memberRepository;
	//@Autowired
	private BoardRepository boardRepository;
	
	//생성자주입 <- (@Autowired 사용안한다면)
	public MemberController(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	/*
	//로그인이 끝난 상태 => 서비스를 이용하는 단계(멤버메인페이지) - 서비스 = 게시물을 보거나 게시물을 등록하는 것 
	@GetMapping("/main")
	public String main() {
		return "member/main";
	}
	//게시물 등록(등록폼, 등록처리) 
	@GetMapping("/regBoard")
	public String regBoard() {
		return "/member/regBoardForm";
	}*/
	
	@PostMapping("/regBoardProc")
	public String regBoardPorc(BoardDto boardDto) { //(파라미터 처리)
		//
		
		Board board = new Board();
		board.setTitle(boardDto.getTitle());
		board.setContent(boardDto.getContent());
		//작성자 데이터 불일치 해결
		Member member = new Member();
		member.setUsername(boardDto.getWriter());
		board.setMember(member);
		
		//파일 관련 멤버변수 세팅 
		String originName = boardDto.getFileName();
		board.setOriginName(originName); //파일원본이름 
		String newName = UUID.randomUUID().toString() + originName; //중복되지 않는 새 파일이름 
		board.setNewName(newName);
		
		
		//파일처리
		File file = new File(newName); //newName으로 파일을 쓰겠다라는 뜻 
		try {
			boardDto.getFile().transferTo(file);
			System.out.println("파일업로드 성공-----");
			
			//썸네일 생성 : 원본크기 파일을 작은 썸네일 크기로 바꿔주는 
			String thumbNailSaveName = "s_" + newName;
			board.setThumbnailName(thumbNailSaveName);
			
			File thumbfile = new File(uploadPath + thumbNailSaveName);
			File ufile = new File(uploadPath + newName);
			//Thumbnails 의존성 주입 
			Thumbnails.of(ufile).size(100, 100).toFile(thumbfile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//db에 board(엔티티)를 저장
		Board b = boardRepository.save(board);
		if(b != null) {
			return "등록성공";
		}else {
			return "등록실패";
		}
	}
	/*
	//게시물 조회
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> list = boardRepository.findAll();
		
		model.addAttribute("boardList", list);
		return "member/boardList";
	}*/
	
	@GetMapping("/detail")
	public String detail(@RequestParam("bno") int bno, Model model) {
		
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.get();
		
		model.addAttribute("board", board);
		
		return "member/boardDetail";
	}
}
