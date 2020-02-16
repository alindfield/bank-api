package com.acme.api.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "transaction_type")
public class TransactionType {

	@Id
	@Column(name = "transaction_type_id")
	private Long id;
	
	@Column(name = "code")	
	private String code;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "debit_credit")
	private String debitCreditCode;
	
	@Transient
	private DebitCredit debitCredit;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	String getDebitCreditCode() {
		return debitCreditCode;
	}

	void setDebitCreditCode(String debitCreditCode) {
		this.debitCreditCode = debitCreditCode;
	}

	public DebitCredit getDebitCredit() {
		return debitCredit;
	}

	public void setDebitCredit(DebitCredit debitCredit) {
		this.debitCredit = debitCredit;
	}
	
	@PostLoad
	void postLoad() {
		if (debitCreditCode != null) {
			this.debitCredit = DebitCredit.of(debitCreditCode);
		}
	}
	
	@PrePersist
	void prepersist() {
		if (debitCredit != null) {
			this.debitCreditCode = debitCredit.getCode();
		}
	}
	
}
