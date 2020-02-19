package com.acme.api.request.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.acme.api.data.Account;
import com.acme.api.data.Balance;
import com.acme.api.data.Transaction;
import com.acme.api.dto.BalanceDTO;
import com.acme.api.dto.InterestDTO;
import com.acme.api.exception.BusinessException;
import com.acme.api.request.repository.AccountRepository;
import com.acme.api.request.repository.BalanceRepository;
import com.acme.api.request.repository.TransactionRepository;
import com.acme.api.request.service.AccountService;

@Component
public class AccountServiceImpl implements AccountService {

	private static Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
		
	@Autowired 
	private AccountRepository accountRepository; 
	
	@Autowired
	private BalanceRepository balanceRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public AccountServiceImpl() {
		LOGGER.info("Created");
	}
	
	@Override
	public BalanceDTO balance(Long sortCode, Long accountNo) {
		Account account = findAccount(sortCode, accountNo);
		Balance balance = balanceRepository.findByAccountId(account.getId());
		Date fromDate = balance == null ? new Date(0) : balance.getBalanceDate();
		LOGGER.info("Balance " + (balance == null ? new Date(0) : balance.getBalanceDate()));
		List<Transaction> transactions = transactionRepository.findAllByAccountIdAndTransactionDateGreaterThan(account.getId(), fromDate, Sort.by(Sort.Direction.ASC, "transactionDate"));
		LOGGER.info("{}", transactions.size());
		Double running = balance == null ? 0D : balance.getAmount();
		Date latest = fromDate;
		for (Transaction t : transactions) {
			running += t.getAmount();
			if (t.getTransactionDate().compareTo(latest) > 0) latest = t.getTransactionDate();
		}
		LOGGER.info("latest {}", latest);
		BalanceDTO balanceDTO = new BalanceDTO();
		balanceDTO.setBalance(running);
		return balanceDTO;
	}

	@Override
	public InterestDTO interest(Long sortCode, Long accountNo) {
		//Account account = findAccount(sortCode, accountNo);
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
