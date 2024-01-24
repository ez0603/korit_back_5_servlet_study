package com.study.servlet_study.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookRepository {
	
	private static BookRepository instance;
	private DBConnectionMgr pool;
		
		public BookRepository() {
			pool = DBConnectionMgr.getInstance();
		}
		
		public static BookRepository getInstance(){
			if(instance == null) {
				instance = new BookRepository();
			}
			return instance;
		}
		
		public int saveBook(Book book) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
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
			
			return 1;
			
		}
		
		public Book findBookByBookId(int bookId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Book findBook = null;
			
			try {
				con = pool.getConnection();
				String sql = "select * from book_view where book_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bookId);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					findBook = Book.builder()
							.bookId(rs.getInt(1))
							.bookName(rs.getString(2))
							.author(Author.builder()
									.authorId(rs.getInt(3))
									.authorName(rs.getString(4))
									.build())
							.publisher(Publisher.builder()
									.publisherId(rs.getInt(5))
									.publisherName(rs.getString(5))
									.build())
							.build();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			
			return findBook;
		}
		
		public List<Book> searchBookList(Map<String, String> params) { 
			
		}
}
