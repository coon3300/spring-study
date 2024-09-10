package com.yedam.app.board.service;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	private	Integer bno;	//게시글 번호
	private String title;	//게시글 제목
	private String contents;//게시글 내용
	private String writer;	//게시글 작성자
	private Date regdate;	//게시글 작성일
	private Date updatedate;//게시글 수정일
	private String image;	//게시글 이미지
}
