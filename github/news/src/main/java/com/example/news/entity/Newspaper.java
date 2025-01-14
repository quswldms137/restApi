package com.example.news.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Newspaper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pno;
	
	private String title;
	private String content;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="writer")
	@ToString.Exclude
	private Member writer; //작성자
	
	private String approvalresult; //승인결과 
	
}
