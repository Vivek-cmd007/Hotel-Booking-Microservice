package com.bookingservice.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullBookingResponse {

	@Positive(message = "Only Positive values Allowed")
	private int bookingId;
	
	@Positive(message="Only Positive values Allowed")
	private int hotelId;
	
	private RoomDetails roomDetails;
	
	private User customer;
	private LocalDate bookedFrom;
	private LocalDate bookedTo;
	
	@Positive(message="Only Positive values Allowed")
	private int noOfAdults;
	@Positive(message="Only Positive values Allowed")
	private int noOfChildren;
	@Positive(message="Only Positive values Allowed")
	private String amount;
	
	private String status;
	

}
