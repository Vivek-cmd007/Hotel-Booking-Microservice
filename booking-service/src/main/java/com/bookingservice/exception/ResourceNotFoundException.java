package com.bookingservice.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}


}
