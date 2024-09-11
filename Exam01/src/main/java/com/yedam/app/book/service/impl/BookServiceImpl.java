package com.yedam.app.book.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.book.mapper.BookMapper;
import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;
import com.yedam.app.book.service.RentVO;

@Service
public class BookServiceImpl implements BookService{
	private BookMapper bookMapper;
	
	@Autowired // 생성자 주입
	public BookServiceImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}
	
	
	@Override
	public List<BookVO> bookList() {
		return bookMapper.selectBookAll();

	}

	@Override
	public List<RentVO> rentList() {
		return bookMapper.selectRent();
	}	
	
	@Override
	public BookVO bookInfo(BookVO bookVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bookInfoNo() {
		// TODO Auto-generated method stub
		return bookMapper.selectBookInfoNo();
	}
	
	@Override
	public int insertBook(BookVO bookVO) {
		return bookMapper.insertBookInfo(bookVO);
	}

	@Override
	public Map<String, Object> updateBook(BookVO bookVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBook(int bookNo) {
		// TODO Auto-generated method stub
		return 0;
	}





}
