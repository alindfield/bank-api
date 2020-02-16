package com.acme.api.request.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.api.dto.BalanceDTO;
import com.acme.api.dto.InterestDTO;

@RestController
@RequestMapping("/account")
public class AccountController {

	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	public AccountController() {
		logger.info("Created");
	}
	
	@GetMapping("/balance/{sortCode}/{accountNo}")
	public BalanceDTO balance(@PathVariable(name = "sortCode", required = true) Long sortCode, @PathVariable(name = "accountNo", required = true) Long accountNo) {
		return null;
	}
	
	@GetMapping("/interest/{sortCode}/{accountNo}")
	public InterestDTO interest(@PathVariable(name = "sortCode", required = true) Long sortCode, @PathVariable(name = "accountNo", required = true) Long accountNo) {
		return null;
	}
}
