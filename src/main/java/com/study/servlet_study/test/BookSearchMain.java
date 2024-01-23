package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;

public class BookSearchMain {
	public static void main(String[] args) {
		
		// 검색 할 도서명을 입력하세요 >>> 글 	도서명에 like 걸어주기
		
		// 도서명 / 저자명 / 출판사
		while(true) 
			{
			DBConnectionMgr pool = DBConnectionMgr.getInstance();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = pool.getConnection();
				String name = "junil";
				String sql = "select * from author_tb where author_name= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				
				rs = pstmt.executeQuery();
				
				List<Author> authorList = new ArrayList<>();
			
				
				while(rs.next()) {
					authorList.add(Author.builder()
							.authorId(rs.getInt(1))
							.authorName(rs.getNString(2))
							.build());
				}
				
				authorList.forEach(author -> System.out.println(author));
				
//				
//				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			}
		
		
	}
}
