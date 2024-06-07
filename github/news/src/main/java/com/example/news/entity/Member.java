package com.example.news.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {
	
	private Integer mno;
	@Id
	private String name; 
	private String type;  //기자=J, 편집자=E
}
