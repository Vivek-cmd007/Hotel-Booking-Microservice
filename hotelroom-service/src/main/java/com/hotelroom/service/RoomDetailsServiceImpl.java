package com.hotelroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelroom.entity.Hotel;
import com.hotelroom.entity.RoomDetails;
import com.hotelroom.exception.ResourceNotFoundException;
import com.hotelroom.payload.RoomDetailsPayload;
import com.hotelroom.repository.RoomDetailsRepository;
import com.hotelroom.repository.HotelRepository;
@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {

	@Autowired
	private RoomDetailsRepository roomDetailsRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	
	@Override
	public RoomDetails getRoomDetailsById(int roomId) {
		Optional<RoomDetails> optionalRoomDetails = roomDetailsRepository.findById(roomId);
		
		if(optionalRoomDetails.isEmpty()) {
			throw new ResourceNotFoundException("Room Details not found"+roomId);
		}
		RoomDetails roomDetails = optionalRoomDetails.get();
		
		return roomDetails;
	}


	@Override
	public void deleteRoomDetails(int roomId) {
		Optional<RoomDetails> optionalRoomDetails = roomDetailsRepository.findById(roomId);
		
		if(optionalRoomDetails.isEmpty()) {
			throw new ResourceNotFoundException("Room Details not found"+roomId);
		}
		RoomDetails roomDetails = optionalRoomDetails.get();
		roomDetailsRepository.delete(roomDetails);
		
	}

	@Override
	public List<RoomDetails> getAllRoomDetails() {
		List<RoomDetails> roomDetails = roomDetailsRepository.findAll();
		return roomDetails;
	}


	@Override
	public RoomDetails createRoomDetails(RoomDetailsPayload roomDetails) {
			Optional<Hotel> hotel = hotelRepository.findById(roomDetails.getHotelId());
			if(hotel.isEmpty()) {
				throw new ResourceNotFoundException("Hotel not found with Id"+roomDetails.getHotelId());			
			}
			
			RoomDetails rooms =new RoomDetails();
			
			rooms.setHotel(hotel.get());
			rooms.setRoomNo(roomDetails.getRoomNo());
			rooms.setRoomType(roomDetails.getRoomType());
			rooms.setRatePerDay(roomDetails.getRatePerDay());
			rooms.setAvailable(roomDetails.isAvailable());
			
			
			return roomDetailsRepository.save(rooms);
			
	}
	
	
	@Override
	public RoomDetails updateRoom(RoomDetails room) {
		Optional<RoomDetails> optionalroom = roomDetailsRepository.findById(room.getRoomId());

		if (optionalroom.isEmpty()) {
			throw new ResourceNotFoundException("Hotel Not found with id "+room.getRoomId());

		}
		
		RoomDetails room2 = optionalroom.get();
		room2.setAvailable(room.isAvailable());
		return roomDetailsRepository.save(room2);
	
	}
	


	
}
