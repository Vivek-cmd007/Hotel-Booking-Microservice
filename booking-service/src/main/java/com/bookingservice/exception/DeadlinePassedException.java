package com.bookingservice.exception;

@SuppressWarnings("serial")
public class DeadlinePassedException extends RuntimeException {

	public DeadlinePassedException() {

	}

	public DeadlinePassedException(String msg) {
		super(msg);
	}
}
