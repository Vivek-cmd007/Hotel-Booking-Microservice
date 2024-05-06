package com.hotelroom.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDetailsPayload {

	@Positive(message = "Only Positive values Allowed")
	private int hotelId;
	
	 @Positive(message="Only Positive values Allowed")
	private String roomNo;
	 
	 @NotBlank(message = "Room Type is mandatory")
	private String roomType;
	 
	 @Positive(message="Only Positive values Allowed")
	private double ratePerDay;
	 
	 @Positive(message="Only Positive values Allowed")
	private boolean available;

}
