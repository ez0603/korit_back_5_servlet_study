package com.study.servlet_study.service;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.repository.AccountRepository;

public class AccountService {
	
	private static AccountService instance;
	private AccountRepository accountRepository;
	
	private AccountService() {
		accountRepository = AccountRepository.getInstance(); // 결합도는 낮다
	}
	
	public static AccountService getInctance() {
		if(instance == null) {
			instance = new AccountService();
		}
		return instance;
	}
	
	public int addAccount(Account account) {
		return accountRepository.saveAccount(account);
	}
	
	public Account getAccount(String username) {
		return accountRepository.findAccountByUsername(username);
	}
	
}
