package com.acme.api.data;

import java.util.stream.Stream;

public enum DebitCredit {

	Debit("D"),
	Credit("C");
	
	private String code;
	
	private DebitCredit(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static DebitCredit of(String code) {
		return Stream.of(DebitCredit.values())
			.filter(p -> p.getCode().equals(code))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
    }
	
}
