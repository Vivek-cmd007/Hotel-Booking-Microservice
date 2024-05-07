package com.bookingservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookingservice.entity.BookingDetails;
import com.bookingservice.model.BookingPayload;
import com.bookingservice.model.FullBookingResponse;

@Service
public interface BookingDetailsService {
	
	 BookingPayload getBookingDetailsById(int bookingId);

	  ResponseEntity<?> createBookingDetails(BookingDetails Booking);
	  
	  List<BookingDetails> getAllBookingDetails();
	  
	  void deleteBookingDetails(int bookingId);
	  
	  BookingDetails updateBookingDetails( BookingDetails bookingDetails);

	  FullBookingResponse getFullBookingDetails(int bookingId);

	ResponseEntity<?> getFullBookingDetailsOfAllIds();
	
	BookingDetails cancelBooking(int bookingId);

//	BookingDetails createBookingDetails(int roomId, int userId, int hotelId);
	  
	  
}
