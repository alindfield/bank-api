package com.acme.api.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.api.data.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}