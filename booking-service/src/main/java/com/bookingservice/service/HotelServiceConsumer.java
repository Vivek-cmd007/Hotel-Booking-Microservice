package com.bookingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.bookingservice.model.Hotel;
import com.bookingservice.model.RoomDetails;

@FeignClient(name="HOTELROOM-SERVICE")
public interface HotelServiceConsumer {
	
	@GetMapping("/hotels/{Id}")
	Hotel getHotelById(@PathVariable("Id") int hotelId);
	
	@GetMapping("/rooms/{Id}")
	RoomDetails getRoomDetailsById(@PathVariable("Id") int roomId);
	
	@PutMapping("/rooms/update")
	RoomDetails updateRoom(RoomDetails room);

}
