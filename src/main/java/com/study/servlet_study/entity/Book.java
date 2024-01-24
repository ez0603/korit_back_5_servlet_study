package com.study.servlet_study.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
	private int bookId;
	private String bookName;
	private Author author;
	private Publisher publisher;
	// 책 한권은 저자, 출판사를 가지고 있어야함
	
	
	
	
	
}
