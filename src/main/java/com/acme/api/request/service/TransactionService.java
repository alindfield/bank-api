package com.acme.api.request.service;

import com.acme.api.dto.TransactionDTO;
import com.acme.api.request.DepositRequest;
import com.acme.api.request.WithdrawalRequest;

public interface TransactionService {

	TransactionDTO deposit(DepositRequest deposit);
	TransactionDTO withdrawal(WithdrawalRequest withdrawal);

}
