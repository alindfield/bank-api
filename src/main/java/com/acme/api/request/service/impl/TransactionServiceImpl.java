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
import com.acme.api.request.repository.TransactionRepository;
import com.acme.api.request.repository.TransactionTypeRepository;
import com.acme.api.request.service.AccountService;
import com.acme.api.request.service.TransactionService;

@Component
@Transactional
public class TransactionServiceImpl implements TransactionService {

	private static Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	
	public TransactionServiceImpl() {
		LOGGER.info("Created");
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
		LOGGER.info("Found Transaction Type Id {} Code {} Description {} Credit/Debit {}", transactionType.getId(), transactionType.getCode(), transactionType.getDescription(), transactionType.getDebitCredit());
		return transactionType;
	}
	
	private TransactionDTO createTransaction(Transaction input) {
		Transaction output = transactionRepository.saveAndFlush(input);
		LOGGER.info("Created Transaction Id {} Account Id {} Amount {} Date {}", output.getId(), output.getAccountId(), output.getAmount(), output.getTransactionDate());
		return TransactionDTO.convertFrom(output);
	}
	
	@Override
	public TransactionDTO deposit(DepositRequest deposit) {
		Account account = accountService.findAccount(deposit.getSortCode(), deposit.getAccountNo());
		TransactionType transactionType = findTransactionType(deposit.getTransactionCode());
		if (transactionType.getDebitCredit() != DebitCredit.Credit) throw new BusinessException("Transaction type must be credit");
		Transaction input = convertToTransaction(account.getId(), transactionType.getId(), deposit.getAmount());
		return createTransaction(input);
	}

	@Override
	public TransactionDTO withdrawal(WithdrawalRequest withdrawal) {
		Account account = accountService.findAccount(withdrawal.getSortCode(), withdrawal.getAccountNo());
		TransactionType transactionType = findTransactionType(withdrawal.getTransactionCode());
		if (transactionType.getDebitCredit() != DebitCredit.Debit) throw new BusinessException("Transaction type must be debit");
		Transaction input = convertToTransaction(account.getId(), transactionType.getId(), withdrawal.getAmount() * -1);
		return createTransaction(input);
	}

}
