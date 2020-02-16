package com.acme.api.request.service;

import com.acme.api.data.Account;
import com.acme.api.dto.BalanceDTO;
import com.acme.api.dto.InterestDTO;

public interface AccountService {

	BalanceDTO balance(Long sortCode, Long accountNo);
	InterestDTO interest(Long sortCode, Long accountNo);
	Account findAccount(Long sortCode, Long accountNo);
	
}
