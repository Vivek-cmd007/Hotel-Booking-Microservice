package com.hotelroom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hotelroom.entity.RoomDetails;
import com.hotelroom.payload.RoomDetailsPayload;

@Service
public interface RoomDetailsService {
	
		RoomDetails getRoomDetailsById(int roomId);

		RoomDetails createRoomDetails(RoomDetailsPayload roomDetails);
		
		void deleteRoomDetails(int roomId);
		
		List<RoomDetails> getAllRoomDetails();
		
		RoomDetails updateRoom(RoomDetails room);
	 
	 

}
