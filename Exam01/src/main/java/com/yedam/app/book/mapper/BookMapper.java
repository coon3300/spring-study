package com.yedam.app.book.mapper;

import java.util.List;

import com.yedam.app.book.service.BookVO;
import com.yedam.app.book.service.RentVO;

public interface BookMapper {
	//전체조회
	public List<BookVO> selectBookAll();
	
	//전체조회
	public List<RentVO> selectRent();
	
	//단건조회
	public BookVO selectBookInfo(BookVO BookVO);
	
	//단건조회
	public int selectBookInfoNo();
	
	//등록
	public int insertBookInfo(BookVO BookVO);
	
	
	//수정
	public int updateBookInfo(BookVO BookVO);
	//삭제
	public int deleteBookInfo(int bookNo);
}
