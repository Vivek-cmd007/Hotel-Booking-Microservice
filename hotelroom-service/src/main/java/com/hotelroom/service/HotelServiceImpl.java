package com.hotelroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelroom.entity.Hotel;
import com.hotelroom.entity.RoomDetails;
import com.hotelroom.exception.ResourceNotFoundException;
import com.hotelroom.repository.HotelRepository;
import com.hotelroom.repository.RoomDetailsRepository;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	public HotelRepository hotelrepository;
	
	@Autowired
	public RoomDetailsRepository roomRepository;
	
	@Override
	public Hotel getHotelById(int hotelId) {
        Optional<Hotel> optionalHotel = hotelrepository.findHotelWithRoomDetailsById(hotelId);
        
        if (optionalHotel.isEmpty()) {
            throw new ResourceNotFoundException("Hotel Not found with id " + hotelId);
        }

        return optionalHotel.get();
    }
	
	@Override
	public Hotel createHotel(Hotel hotel) {	    
	    Hotel savedHotel = hotelrepository.save(hotel);
	   
	    if (hotel.getRoomDetails() != null) {
	        for (RoomDetails room : hotel.getRoomDetails()) {
	            room.setHotel(savedHotel); 
	        }
	    }	  
	    savedHotel = hotelrepository.save(savedHotel);

	    return savedHotel;
	}	

	@Override
	public List<Hotel> getAllHotel() {
		List<Hotel> hotels = hotelrepository.findAll();
		return hotels;
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		Optional<Hotel> optionalhotel = hotelrepository.findById(hotel.getHotelId());

		if (optionalhotel.isEmpty()) {
			throw new ResourceNotFoundException("Hotel Not found with id "+hotel.getHotelId());

		}
		hotelrepository.save(hotel);
		return hotel;
	}

	@Override
	public void deleteHotel(int hotelId) {
		
		Optional<Hotel> optionalhotel = hotelrepository.findById(hotelId);

		if (optionalhotel.isEmpty()) {
			throw new ResourceNotFoundException("Hotel Not found with id "+hotelId);

		}
		Hotel hotels = optionalhotel.get();
		hotelrepository.delete(hotels);
	}

	@Override
	 public List<RoomDetails> findRoomsByHotelId(int hotelId) {
        Optional<Hotel> optionalHotel = hotelrepository.findById(hotelId);
        
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            return hotel.getRoomDetails();
        } else {
            throw new ResourceNotFoundException("Hotel not found with ID: " + hotelId);
        }
    }

}
