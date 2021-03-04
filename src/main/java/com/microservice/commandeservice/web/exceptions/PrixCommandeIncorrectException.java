package com.microservice.commandeservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PrixCommandeIncorrectException extends RuntimeException {

	public PrixCommandeIncorrectException(String s) {
		super(s);
	}
}
