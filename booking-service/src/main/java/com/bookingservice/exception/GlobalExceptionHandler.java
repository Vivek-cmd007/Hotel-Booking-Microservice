package com.bookingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(Exception e){
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		return responseEntity;

}
	
	@ExceptionHandler(DeadlinePassedException.class)
	public ResponseEntity<String> handleDeadlinePassedException(Exception e){
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		return responseEntity;
}
}