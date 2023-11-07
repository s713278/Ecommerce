package com.srtech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalException {

	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotForundException(ProductNotFoundException exception) {
		log.debug("Caught ProductNotFoundException and Message is {}"+exception.getMessage());
		//emailService.sendMail("admin@gmail.com");
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
