package com.study.servlet_study.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/*")
public class RequestCharatorEncodingFilter extends HttpFilter implements Filter {
       

	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String[] methods = new String[] { "POST", "PUT" }; // 생성하자마자 바로 주입
		
		if(Arrays.asList(methods).contains(httpRequest.getMethod().toUpperCase())) { // 리스트에 요청된 메소드를 넣어줌 , toUpperCase = 무조건 대문자로 만들어줌(소문자는 인식이 안되기때문에 바꿔줌)
			httpRequest.setCharacterEncoding("utf-8");
		} // true 일때만 
		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
