package com.acme.api.utilities;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.acme.api.data.Account;
import com.acme.api.exception.BusinessException;
import com.acme.api.request.repository.AccountRepository;
import com.acme.api.response.Response;

public class ControllerUtilities {

	private static Logger LOGGER = LoggerFactory.getLogger(ControllerUtilities.class);
	
	private ControllerUtilities() {
	}
	
	public static interface Caller<F> {
		F doCall();
	}
	
	public static <E> ResponseEntity<Response<E>> call(Caller<E> caller, BindingResult result) {
		HttpStatus status = HttpStatus.OK;
		Response<E> response = new Response<>();	
		
		if (result != null && result.hasErrors()) {
			result.getAllErrors().stream().forEach(oe -> {response.getErrors().add(oe.getDefaultMessage());});
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
			return ResponseEntity.status(status).body(response);
		}
		
		try {
			E output = caller.doCall();
			response.setPayload(output);
		} catch (BusinessException ex) {
			LOGGER.error("Business Exception", ex);
			response.setSuccess(false);
			response.getErrors().add(ex.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch (DataAccessException ex) {
			LOGGER.error("Data Access Exception", ex);
			response.setSuccess(false);
			response.getErrors().add(ex.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch (PersistenceException ex) {
			LOGGER.error("Persistence Exception", ex);
			response.setSuccess(false);
			response.getErrors().add(ex.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status).body(response);	
	}
		
}
