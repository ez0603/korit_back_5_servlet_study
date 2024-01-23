package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;

public class DBConnectionTestMain {
	
	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String name = "junil";
			String sql = "select * from author_tb where author_name= ?"; // name = scanner 일때 like concat(\"'", ?, \"'")해주면 비슷한 결과 도출
			// 쿼리를 붙여넣고 싶으면 mysql에서 쿼리 작성 후 붙어넣기
			pstmt = con.prepareStatement(sql); // 쿼리창에 붙여넣기
			pstmt.setString(1, name); // ?에 알아서 넣어줌 (표현식) 1 = ? 순서
			
			rs = pstmt.executeQuery(); // executeQuery = 실행 -> 결과를 ResultSet안에 담는다
			
			List<Author> authorList = new ArrayList<>(); // ResultSet에 있는 결과를 리스트로 만들어줌
		
			
			while(rs.next()) { // rs.next() = 커서 이동 -> 한번 호출할 때마다 있으면 내려옴 다음이 없을때까지 전체반복
//				System.out.println("id : " + rs.getInt(1)); // 1 = 컬럼 첫번째를 의미
//				System.out.println("name : " + rs.getString(2)); 
				authorList.add(Author.builder()
						.authorId(rs.getInt(1))
						.authorName(rs.getNString(2))
						.build());
			}
			
			authorList.forEach(author -> System.out.println(author));
			
//			for(Author author : authorList) { // 위의 람다식과 동일한 결과
//				System.out.println(author);
//			}
//			for(int i = 0; i < authorList.size(); i++) { // 위의 람다식과 동일한 결과
//				Author author= authorList.get(i);
//				System.out.println(author);
//			}
//			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
}
