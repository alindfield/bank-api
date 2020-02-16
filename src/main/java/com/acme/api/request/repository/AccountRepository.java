package com.acme.api.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.api.data.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findAccountBySortCodeAndAccountNo(Long sortCode, Long accountNo);
}
