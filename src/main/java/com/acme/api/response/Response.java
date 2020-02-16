package com.acme.api.response;

import java.util.ArrayList;
import java.util.List;

public class Response<E> {

	private boolean success = true;
	private List<String> errors = new ArrayList<String>();
	private E payload;
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public E getPayload() {
		return payload;
	}
	
	public void setPayload(E payload) {
		this.payload = payload;
	}
	
}