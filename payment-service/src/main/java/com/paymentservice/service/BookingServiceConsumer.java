package com.paymentservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.paymentservice.model.BookingResponse;

@FeignClient(name="BOOKING-SERVICE")
public interface BookingServiceConsumer {
	
	@GetMapping("/bookings/getFullBookingDetails/{bookingId}")
	 BookingResponse getFullBookingDetails(@PathVariable("bookingId") int bookingId);
	

}
