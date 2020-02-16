package com.acme.api.request.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.api.data.Account;
import com.acme.api.dto.BalanceDTO;
import com.acme.api.dto.InterestDTO;
import com.acme.api.exception.BusinessException;
import com.acme.api.request.repository.AccountRepository;
import com.acme.api.request.service.AccountService;

@Component
public class AccountServiceImpl implements AccountService {

	private static Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired 
	private AccountRepository accountRepository; 
	
	public AccountServiceImpl() {
		LOGGER.info("Created");
	}
	
	@Override
	public BalanceDTO balance(Long sortCode, Long accountNo) {
		Account account = findAccount(sortCode, accountNo);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterestDTO interest(Long sortCode, Long accountNo) {
		Account account = findAccount(sortCode, accountNo);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccount(Long sortCode, Long accountNo) {
		Account account = accountRepository.findAccountBySortCodeAndAccountNo(sortCode, accountNo);
		if (account == null) throw new BusinessException("Inavlid account");
		LOGGER.info("Found Account Id {} Customer Id {} Sort Code {} Account No {}", account.getId(), account.getCustomerId(), account.getSortCode(), account.getAccountNo());
		return account;
	}
}
