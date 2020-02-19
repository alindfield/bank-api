package com.acme.api.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BalanceProcessor {

	private static Logger logger = LoggerFactory.getLogger(BalanceProcessor.class);
	
	@Scheduled(cron = "${balanceprocesssor.cron}")
	void x() {
		logger.info("Calculate Balances");
	}
}
