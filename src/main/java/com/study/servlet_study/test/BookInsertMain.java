package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookInsertMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String bookName = null;
		String authorName = null;
		String publisherName = null;
		
		System.out.print("도서명 >>> ");
		bookName = scanner.nextLine();
		System.out.print("저자명 >>> ");
		authorName = scanner.nextLine();
		System.out.print("출판사 >>> ");
		publisherName = scanner.nextLine();
		
		Book book = Book.builder() // book객체 만들기
				.bookName(bookName)
				.author(Author.builder().authorName(authorName).build())
				.publisher(Publisher.builder().publisherName(publisherName).build())
				.build();
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			con = pool.getConnection();
			String sql = "insert into author_tb values (0,?)"; 
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, book.getAuthor().getAuthorName()); 
			pstmt.executeUpdate(); // insert, update, delete => executeUpdate
			ResultSet rs = pstmt.getGeneratedKeys(); // 자동증가된 키값을 알고싶을때 -> 방금 집어넣은 id값을 ResultSet으로 가져옴, 데이터가 여러개면 while문 돌리기
			if(rs.next()) {
				book.getAuthor().setAuthorId(rs.getInt(1)); // book이라는 객체안에 Author가 들어있기 때문에 set이 아닌 getAuthor, rs.getInt(1) -> 방금 만들어진 키값
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		try {
			con = pool.getConnection();
			String sql = "insert into publisher_tb values (0,?)"; 
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, book.getPublisher().getPublisherName()); 
			pstmt.executeUpdate(); 
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				book.getPublisher().setPublisherId(rs.getInt(1)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		try {
			con = pool.getConnection();
			String sql = "insert into book_tb values (0, ?, ?, ?)"; // book_tb은 PublisherId AuthorId가 있어야하기 때문
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, book.getBookName()); 
			pstmt.setInt(2, book.getAuthor().getAuthorId());
			pstmt.setInt(3, book.getPublisher().getPublisherId());
			pstmt.executeUpdate(); 
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				book.setBookId(rs.getInt(1)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		System.out.println("추가된 도서 정보");
		System.out.println(book);
		
		
	}
}
