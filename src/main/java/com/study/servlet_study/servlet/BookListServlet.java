package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.service.BookService;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService;
       

    public BookListServlet() {
        super();
        bookService = BookService.getInstance();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// params
		// bookName -> like 조회
		// authorName -> like 조회
		// publisherName -> like 조회
		// bookName이 있을때 where조건에 bookName -> book_name like '%'
		// bookName authorName -> or 조건
		// bookName authorName -> like 일치
		// null이 아닌것으로 조회
		// params를 아무것도 입력 안했을때 -> 싹다조회
		// bookName authorName publisherName 중 하나만 입력해도 검색 되어야함
		// where에 book_name like '%' or author_name like '%' 두가지인데 params가 1개만 들어왔을때 => 1 = 1 and (not 1=1 or book_name like '%') and (not 1=1 or author_name like '%')-> book_name 조건이 있을때 
		
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String publisherName = request.getParameter("publisherName");
		
		Map<String, String> params = new HashMap<>();
		if(bookName != null) {
			params.put("bookName", bookName);
		}
		if(authorName != null) {
			params.put("authorName", authorName);
		}
		if(publisherName != null) {
			params.put("publisherName", publisherName);
		}
		
		params.size();
		
	}

}
