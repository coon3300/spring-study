package com.yedam.app.book.service;

import lombok.Data;

@Data
public class RentVO {
	private Integer bookNo;
	private String bookName;
	private Integer rentSum;
	private Integer rentCount;
}
