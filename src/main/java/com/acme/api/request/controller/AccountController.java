package com.acme.api.request.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.api.dto.BalanceDTO;
import com.acme.api.dto.InterestDTO;
import com.acme.api.request.service.AccountService;
import com.acme.api.response.Response;
import com.acme.api.utilities.ControllerUtilities;

@RestController
@RequestMapping("/account")
public class AccountController {

	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
		
	public AccountController() {
		logger.info("Created");
	}
	
	@GetMapping("/balance/{sortCode}/{accountNo}")
	public ResponseEntity<Response<BalanceDTO>> balance(@PathVariable(name = "sortCode", required = true) Long sortCode, @PathVariable(name = "accountNo", required = true) Long accountNo) {
		return ControllerUtilities.call(() -> accountService.balance(sortCode, accountNo), null);
	}
	
	@GetMapping("/interest/{sortCode}/{accountNo}")
	public ResponseEntity<Response<InterestDTO>> interest(@PathVariable(name = "sortCode", required = true) Long sortCode, @PathVariable(name = "accountNo", required = true) Long accountNo) {
		return ControllerUtilities.call(() -> accountService.interest(sortCode, accountNo), null);
	}
}
