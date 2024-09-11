package com.yedam.app.book.service;

import java.util.List;
import java.util.Map;

public interface BookService {
	//전체조회
	public List<BookVO> bookList();
	
	//렌트조회
	public List<RentVO> rentList();
	
	//단건조회
	public BookVO bookInfo(BookVO bookVO);
	
	//단건조회
	public int bookInfoNo();
	
	//등록
	public int insertBook(BookVO bookVO);
	
	
	
	//수정
	public Map<String, Object> updateBook(BookVO bookVO);
	
	//삭제
	public int deleteBook(int bookNo);
	

}
