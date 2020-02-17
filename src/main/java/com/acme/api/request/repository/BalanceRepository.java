package com.acme.api.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acme.api.data.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
	Balance findByAccountId(Long accountId);
	@Query(value = "SELECT b1 FROM Balance b1 WHERE b1.accountId = ?1") 
	Balance test(Long accountId);
}
