package com.acme.api.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DepositRequest {

	private Long sortCode;
	private Long accountNo;
	private Double amount;
	private String transactionCode;
	
	@NotNull(message = "Sort code cannot be null")
	@Min(value = 100000, message = "Sort code is six digits")
	@Max(value = 999999, message = "Sort code is six digits")
	public Long getSortCode() {
		return sortCode;
	}
	
	public void setSortCode(Long sortCode) {
		this.sortCode = sortCode;
	}
	
	@NotNull(message = "Account no cannot be null")
	@Min(value = 10000000, message = "Account no is eight digits")
	@Max(value = 99999999, message = "Account no is eight digits")
	public Long getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	
	@NotNull(message = "Account no cannot be null")
	@Min(value = (long)0.01, message = "Amount must me at least 1p")
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@NotNull(message = "Transaction code cannot be null")
	@Size(min = 1, max = 3, message = "Transaction code must be 1 to 3 characters")
	public String getTransactionCode() {
		return transactionCode;
	}
	
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
		
}
