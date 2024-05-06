package com.bookingservice.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Hotel {


	private int hotelId;	

	private String city;	

	private String hotelName;	
	
	private String address;

	private String description;	
	
	private double avgRatePerDay;
	
	private String email;		

	private String hotelPhone1;
		
	private String hotelPhone2;

	private String website;
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)

	private List<RoomDetails> roomDetails;
	

}
