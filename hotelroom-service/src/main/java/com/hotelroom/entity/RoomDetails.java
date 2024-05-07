package com.hotelroom.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ROOM_DETAILS")
public class RoomDetails {
	
	  @Id
	  @Column(name="room_id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int roomId;

	  @ManyToOne
	  @JoinColumn(name = "hotel_id")
	  @JsonIgnore
	  private Hotel hotel;

	  @Positive(message="Only Positive values Allowed")
	  @Column(name="room_no")
	  private String roomNo;

	  @NotBlank(message = "Room Type is mandatory")
	  @Column(name="room_type")
	  private String roomType;
	  
	  @Positive(message="Only Positive values Allowed")
	  @Column(name="rate_per_day")
	  private double ratePerDay;

	  
	  private boolean available;


}
