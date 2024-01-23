package com.study.servlet_study.repository;

import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.entity.Account;

public class AccountRepository {
	
	private List<Account> accountList;
	private static AccountRepository instance; // 자기자신을 담을 수 있는 instance 객체
	
	private AccountRepository() { // 다른곳에서 호출되면 안되기 때문에 private로 호출
		accountList = new ArrayList<>();
	}
	
	public static AccountRepository getInstance() { //
		if(instance == null) { // instance가 비어있을때만 생성해줘야하기 때문에 if문 써주기
			instance = new AccountRepository();
		}
		return instance;
	}

	public int saveAccount(Account account) {
		accountList.add(account);
		return 1;
	}
	
	public Account findAccountByUsername(String username) { // username으로 찾아줌
		Account findAccount = null; // findAccount 초기화
		
		for(Account account : accountList) {
			if(account.getUsername().equals(username)) {
				findAccount = account; // 찾은 account가 맞다면
				break; // 멈추기
			}
		}
		return findAccount; // 찾지 못하면 null 리턴
	}
}
