package com.yedam.app.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;
import com.yedam.app.book.service.RentVO;

@Controller
public class BookController {

	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("bookList") 
	public String bookList(Model model) {
		List<BookVO> list = bookService.bookList();
		
		model.addAttribute("books", list);
		
		return "book/bookList";
	}
	
	@GetMapping("rentList") 
	public String rentList(Model model) {
		List<RentVO> list = bookService.rentList();
		
		model.addAttribute("rents", list);
		
		return "book/rentList";
	}

	
	@GetMapping("bookInsert")
	public String bookInsertForm(Model model) {
		
		int bookNo = bookService.bookInfoNo();
		model.addAttribute("bookNo", bookNo);		
		
		return "book/bookInsert";
	}
	
	@PostMapping("bookInsert")
	public String bookInsertProcess(BookVO bookVO) {
		bookService.insertBook(bookVO);
		
		return "redirect:bookList";
	}	
	
}
