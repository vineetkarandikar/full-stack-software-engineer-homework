package com.coding.examination.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = DemoApplicationException.class)
	public ResponseEntity<?> DigitalOnboardingException(DemoApplicationException demoApplicationException) {
		ExceptionResponse apiError = new ExceptionResponse();
		apiError.setDebugMessage("NA");
		apiError.setTimestamp(LocalDateTime.now());
		apiError.setMessage(demoApplicationException.getMessage());
		apiError.setStatus(demoApplicationException.getStatus());
		return new ResponseEntity<>(apiError, apiError.getStatus());

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> databaseConnectionFailsException(Exception exception) {
		ExceptionResponse apiError = new ExceptionResponse();
		apiError.setMessage(exception.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setDebugMessage(exception.getStackTrace().toString());
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
