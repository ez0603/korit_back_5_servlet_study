package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.utila.ParamsConvert;


@WebServlet("/http")
public class HttpStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HttpStudyServlet() {
        super();
 
    }
    
    // HTTP 메소드
    // POST요청 	-> 	C reate(추가)
    // GET요청		->	R ead(조회)
    // PUT요청		-> 	U pdate(수정)
    // DELETE요청 	->  D elete(삭제)
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request -> 요청관련 데이터 / 클라이언트 -> 서버 -> DB
		// 한글깨짐은 들어올때 문제이기 때문에 
//		request.setCharacterEncoding("uth-8"); // filter에 만들어줌
		
		Map<String, String> paramsMap = new HashMap<>();
		
		request.getParameterMap().forEach((k, v) -> {  // type = string 
			StringBuilder builder = new StringBuilder(); // 비어있는 문자열의 공간을 만들어줌
			
			Arrays.asList(v).forEach(value -> builder.append(value)); // 배열을 리스트 자료형으로 바꿔줌, 리스트에서 value를 하나씩 담아 합친다
			
//			System.out.println(k + ":" + builder.toString()); // 문자열로 바꿔줌 toString을 하지않으면 주소값이 나옴
			
			paramsMap.put(k, builder.toString());
		});
		
		System.out.println(paramsMap);
		System.out.println(request.getParameter("name"));
		
		Map<String, String> paramsMap2 = new HashMap<>();
		Iterator<String> ir = request.getParameterNames().asIterator();
		while (ir.hasNext()) {
			String key = ir.next();
			paramsMap2.put(ir.next(), getInitParameter(key));
		}
		// 요청할때 데이터 타입은 문자열이다.
		 // cntr + alt + 방향키 => 복사
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인은 Get X , Post로
		Map<String, String> paramsMap = ParamsConvert.convertParamsMapToMap(request.getParameterMap());
		
		System.out.println(paramsMap);
	}


	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

}
