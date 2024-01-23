package com.study.servlet_study.test;

import java.util.function.Consumer;

import com.study.servlet_study.entity.Author;

class Print<T> implements Consumer<T> {
	@Override // 추상메소드이기 때문에 무조건 오버라이드
	public void accept(T t) {
		System.out.println(t); // 매개변수를 받아 출력
	}
}

public class LamdaMain {
	
	public static void main(String[] args) {
		Consumer<Author> print0 = new Print<Author>(); // print라는 클래스로 consumer에 업케스팅
		// -> 주소값 대입
		
		Consumer<Author> print1 = new Consumer<Author>() { // 인터페이스
			@Override // 임시로 구현
			public void accept(Author author) { // 자료형 일치 시켜주기
				System.out.println(author);
			} // -> 생성된 객체 주소값
		};
		
		Consumer<Author> print2 = (author) -> System.out.println(author); // 람다 = 추상메소드 무조건 1개 , 객체 생성 (익명클래스의 객체생성을 줄이는것) 
		// -> 실행되는것 X , 정의되는것이다 / Consumer<Author> print2로 정의해주었기(메모리 주소의 형태가 정해져있기) 때문에 가능한것 ,  (author) -> 로 축약한것
		
		print0.accept(Author.builder().authorId(1).authorName("김준일").build()); 
		print1.accept(Author.builder().authorId(2).authorName("김준이").build()); 
		print2.accept(Author.builder().authorId(3).authorName("김준삼").build()); 
		
		forEach(print2);
		
		forEach(author -> { // 메모리 주소의 형태가 정해져있기 때문에 가능한것
			System.out.println("<<< test >>>");
			System.out.println(author); // = accept
			}); 
		
	}
	
	public static void forEach(Consumer<Author> action) { // action = print2  메모리 주소의 형태가 정해져있기 때문에 가능한것
		action.accept(Author.builder().authorId(4).authorName("김준사").build()); // 주소를 넘겨주는것
	}
}
