package com.userservice.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}


}
