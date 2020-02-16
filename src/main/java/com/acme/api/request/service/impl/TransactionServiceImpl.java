package com.acme.api.request.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.acme.api.data.Account;
import com.acme.api.data.DebitCredit;
import com.acme.api.data.Transaction;
import com.acme.api.data.TransactionType;
import com.acme.api.dto.TransactionDTO;
import com.acme.api.exception.BusinessException;
import com.acme.api.request.DepositRequest;
import com.acme.api.request.WithdrawalRequest;
import com.acme.api.request.repository.AccountRepository;
import com.acme.api.request.repository.TransactionRepository;
import com.acme.api.request.repository.TransactionTypeRepository;
import com.acme.api.request.service.TransactionService;

@Component
@Transactional
public class TransactionServiceImpl implements TransactionService {

	private static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired 
	private AccountRepository accountRepository; 
	
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	
	public TransactionServiceImpl() {
		logger.info("Created");
	}
	
	private Account findAccount(Long sortCode, Long accountNo) {
		Account account = accountRepository.findAccountBySortCodeAndAccountNo(sortCode, accountNo);
		if (account == null) throw new BusinessException("Inavlid account");
		logger.info("Found Account Id {} Customer Id {} Sort Code {} Account No {}", account.getId(), account.getCustomerId(), account.getSortCode(), account.getAccountNo());
		return account;
	}
	
	private Transaction convertToTransaction(Long accountId, Long transactionTypeId, Double amount) {
		Transaction transaction = new Transaction();
		transaction.setAccountId(accountId);
		transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
		transaction.setTransactionTypeId(transactionTypeId);
		transaction.setAmount(amount);
		return transaction;
	}

	private TransactionType findTransactionType(String transactionCode) {
		TransactionType transactionType = transactionTypeRepository.findByCode(transactionCode);
		if (transactionType == null) throw new BusinessException("Inavlid transaction type");
		logger.info("Found Transaction Type Id {} Code {} Description {} Credit/Debit {}", transactionType.getId(), transactionType.getCode(), transactionType.getDescription(), transactionType.getDebitCredit());
		return transactionType;
	}
	
	@Override
	public TransactionDTO deposit(DepositRequest deposit) {
		Account account = findAccount(deposit.getSortCode(), deposit.getAccountNo());
		TransactionType transactionType = findTransactionType(deposit.getTransactionCode());
		if (transactionType.getDebitCredit() != DebitCredit.Credit) throw new BusinessException("Transaction type must be credit");
		Transaction input = convertToTransaction(account.getId(), transactionType.getId(), deposit.getAmount());
		Transaction output = transactionRepository.saveAndFlush(input);
		return TransactionDTO.convertFrom(output);
	}

	@Override
	public TransactionDTO withdrawal(WithdrawalRequest withdrawal) {
		Account account = findAccount(withdrawal.getSortCode(), withdrawal.getAccountNo());
		TransactionType transactionType = findTransactionType(withdrawal.getTransactionCode());
		if (transactionType.getDebitCredit() != DebitCredit.Debit) throw new BusinessException("Transaction type must be debit");
		Transaction input = convertToTransaction(account.getId(), transactionType.getId(), withdrawal.getAmount());
		Transaction output = transactionRepository.saveAndFlush(input);
		return TransactionDTO.convertFrom(output);
	}

}
