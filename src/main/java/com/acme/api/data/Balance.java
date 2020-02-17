package com.acme.api.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "balance")
public class Balance {

	@Id
	@SequenceGenerator(name = "balance_seq", sequenceName="balance_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "balance_seq")
	@Column(name = "balance_id")
	private Long id;
	
	@Column(name = "account_id")
	private Long accountId;
	
	@Column(name = "balance_date")
	private Date balanceDate;
	
	@Column(name = "amount")
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
	
	public Date getBalanceDate() {
		return balanceDate;
	}
	
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
