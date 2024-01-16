package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello") // 요청 주소 확인
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getMethod()); // GET 출력
		System.out.println(request.getRequestURL()); // 전체 다 들고옴
		System.out.println(request.getRequestURI()); // 앞주소를 빼고 뒷부분만 들고옴
		System.out.println(response.getStatus()); 
		
		response.setContentType("text/plain");
		
		System.out.println(response.getContentType()); // 응답된 데이터가 없을 땐 null 출력
		
		response.getWriter().println("헬로");
		
		System.out.println("요청이 들어옴 !");
		
		
	}

}
