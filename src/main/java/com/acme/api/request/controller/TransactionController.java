package com.acme.api.request.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.api.dto.TransactionDTO;
import com.acme.api.request.DepositRequest;
import com.acme.api.request.WithdrawalRequest;
import com.acme.api.request.service.TransactionService;
import com.acme.api.response.Response;
import com.acme.api.utilities.ControllerUtilities;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	private static Logger logger = LoggerFactory.getLogger(TransactionController.class);
		
	public TransactionController() {
		logger.info("Created");
	}
	
	@PostMapping(value = "/deposit")
	public ResponseEntity<Response<TransactionDTO>> deposit(@Valid @RequestBody DepositRequest deposit, BindingResult result) {
		return ControllerUtilities.call(() -> transactionService.deposit(deposit), result) ;
	}

	@PostMapping(value = "/withdrawal")
	public ResponseEntity<Response<TransactionDTO>> withdrawal(@Valid @RequestBody WithdrawalRequest withdrawal, BindingResult result) {
		return ControllerUtilities.call(() -> transactionService.withdrawal(withdrawal), result) ;
	}
}
