package com.bookingservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
	
	@Pattern(regexp = "^[0-9]{10}", message = "Invalid Mobile Number")
	private String mobile;
	
	@Pattern(regexp = "^[0-9]{10}", message = "Invalid Mobile Number")
	private String hotelPhone1;
	


}
