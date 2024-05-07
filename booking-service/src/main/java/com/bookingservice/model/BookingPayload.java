package com.bookingservice.model;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingPayload {

	@NotBlank(message = "Hote Name is mandatory")
	private String hotelName;
	
	@Positive(message="Only Positive values Allowed")
	private String roomNo;
	
	@NotBlank(message = "Customer name is mandatory")
	private String customer;
	
	@Positive(message="Only Positive values Allowed")
	private String amount;
	
	@NotBlank(message = "Room Type name is mandatory")
	private String roomType;
	
	 @Size(min=0,max=10)
	private String mobile;
	
	 @Size(min=0,max=10)
	private String hotelPhone1;
	


}
