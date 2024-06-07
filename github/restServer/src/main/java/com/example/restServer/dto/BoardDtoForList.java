package com.example.restServer.dto;

import lombok.Data;

@Data
public class BoardDtoForList {
	private Integer bno;
	private String title;
	private String content;
	
	private String writer;
	//파일처리 
	private String originName;
	private String newName;
	private String thumbnailName;
}
