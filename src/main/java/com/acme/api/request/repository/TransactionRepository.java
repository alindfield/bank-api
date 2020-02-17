package com.acme.api.request.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.api.data.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findAllByAccountIdAndTransactionDateGreaterThan(Long accountId, Date fromDate, Sort sort);
}