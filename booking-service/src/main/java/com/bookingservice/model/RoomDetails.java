package com.bookingservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDetails {
	
	  private int roomId;
	 
	  private int hotelId;
	 
	  private String roomNo;
	
	  private String roomType;
	
	  private double ratePerDay;

	  private boolean available;

//	  @Lob
//	  private byte[] photo;

//	  @OneToMany(mappedBy = "roomDetails")
//	  private List<BookingDetails> bookingDetails;


}
