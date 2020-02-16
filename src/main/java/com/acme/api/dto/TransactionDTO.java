package com.acme.api.dto;

import java.sql.Timestamp;

import com.acme.api.data.Transaction;

public class TransactionDTO {

	private Long id;
	private Long accountId;
	private Timestamp transactionDate;
	private Long transactionTypeId;
	private Double amount;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public Timestamp getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public Long getTransactionTypeId() {
		return transactionTypeId;
	}
	
	public void setTransactionTypeId(Long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public static TransactionDTO convertFrom(Transaction transaction) {
		TransactionDTO result = new TransactionDTO();
		result.setId(transaction.getId());
		result.setAccountId(transaction.getAccountId());
		result.setTransactionDate(transaction.getTransactionDate());
		result.setTransactionTypeId(transaction.getTransactionTypeId());
		result.setAmount(transaction.getAmount());
		return result;
	}
}
