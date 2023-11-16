package com.srtech.exception;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.srtech.dto.ExcceptionDetails;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalException {

	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<ExcceptionDetails> handleProductNotForundException(ProductNotFoundException exception) {
		log.debug("Caught ProductNotFoundException and Message is {}"+exception.getMessage());
		//emailService.sendMail("admin@gmail.com");
		ExcceptionDetails details=ExcceptionDetails.builder()
				.exceptionCause(exception.getMessage())
				.userMessage("Product name is invalid,Please user the correct one.")
				.localTime(LocalTime.now())
				.build();
		return new ResponseEntity<ExcceptionDetails>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ExcceptionDetails> defaultException(RuntimeException exception) {
		log.debug("Caught ProductNotFoundException and Message is {}"+exception.getMessage());
		//emailService.sendMail("admin@gmail.com");
		ExcceptionDetails details=ExcceptionDetails.builder()
				.exceptionCause(exception.getMessage())
				.localTime(LocalTime.now())
				.build();
		return new ResponseEntity<ExcceptionDetails>(details,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<ExcceptionDetails> hadnleEntityNotFoundException(EntityNotFoundException exception) {
		log.debug("Caught ProductNotFoundException and Message is {}"+exception.getMessage());
		//emailService.sendMail("admin@gmail.com");
		ExcceptionDetails details=ExcceptionDetails.builder()
				.exceptionCause(exception.getMessage())
				.userMessage("REcord not existed in database with mentioned id")
				.localTime(LocalTime.now())
				.build();
		return new ResponseEntity<ExcceptionDetails>(details,HttpStatus.NOT_FOUND);
	}
	
}
