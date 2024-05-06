package com.hotelroom.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="HOTEL")
public class Hotel {

	@Id
	@Column(name="hotel_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int hotelId;	
	
	@NotBlank(message = "City is mandatory")
	private String city;
	
	@Column(name="hotel_name")
	@NotBlank(message = "Name is mandatory")
	private String hotelName;	
	
	@NotBlank(message = "Address is mandatory")
	private String address;
	
	private String description;
	
	@Column(name="avg_rate_per_day")
	@Positive(message="Only Positive values Allowed")
	private double avgRatePerDay;
	
	@Email(message="Enter a Valid Email")
	private String email;	
	
	@Pattern(regexp = "^[0-9]{10}", message = "Invalid Mobile Number")
	@Column(name="phone_1")
	private String phone1;
	
	@Pattern(regexp = "^[0-9]{10}", message = "Invalid Mobile Number")
	@Column(name="phone_2")
	private String phone2;

	@NotBlank(message = "Address is mandatory")
	private String website;
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<RoomDetails> roomDetails;
	

}
