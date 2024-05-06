package com.bookingservice.entity;


import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="BOOKING_DETAIL")
public class BookingDetails {
	
	
	  @Id
	  @Column(name="booking_id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int bookingId;

	  @NotNull(message = "Enter a valid  Id")
	  @Column(name = "hotel_id")
	  private int hotelId;

	  @NotNull(message = "Enter a valid  Id")
	  @Column(name = "room_id")
	  private int roomId;

	  @NotNull(message = "Enter a valid  Id")
	  @Column(name = "user_id")
	  private int userId;
	  
	  @NotNull
	  @CreationTimestamp
	  private LocalDate bookingDate;

	  @FutureOrPresent(message = "bookingFromDate must be in the present or future")
	  private LocalDate bookedFrom;

	  @FutureOrPresent(message = "bookingFromDate must be in the present or future")
	  private LocalDate bookedTo;

	  @Positive(message="Only Positive values Allowed")
	  @Column(name="no_of_adult")
	  private int noOfAdults;

	  @Positive(message="Only Positive values Allowed")
	  @Column(name="no_of_children")
	  private int noOfChildren;

	  @Positive(message="Only Positive values Allowed")
	  private String amount;
	  
	  private String status;
//
//	  @OneToMany(mappedBy = "bookingDetails")
//	  private List<Payment> payments;

	

}
