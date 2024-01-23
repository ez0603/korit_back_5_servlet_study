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
			String sql = "select * from author_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // executeQuery = 실행
			
			List<Author> authorList = new ArrayList<>();
		
			
			while(rs.next()) {
//				System.out.println("id : " + rs.getInt(1)); // 1 = 컬럼 첫번째를 의미
//				System.out.println("name : " + rs.getString(2)); 
				authorList.add(Author.builder()
						.authorId(rs.getInt(1))
						.authorName(rs.getNString(2))
						.build());
			}
			
			authorList.forEach(author -> System.out.println(author));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
}
