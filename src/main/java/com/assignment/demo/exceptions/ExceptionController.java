package com.assignment.demo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleControllerExceptions(Exception e) {
		return new ResponseEntity<String>("The request URL has some syntax error", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	ResponseEntity<String> handleServiceExceptions(ServiceException e) {
		return new ResponseEntity<String>("Service is unavaible for the request due to internal error",
				HttpStatus.SERVICE_UNAVAILABLE);
	}

}
