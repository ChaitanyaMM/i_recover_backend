package com.irecover.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DataConflictException extends  RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataConflictException(String message) {
		super(message);

	}
}
