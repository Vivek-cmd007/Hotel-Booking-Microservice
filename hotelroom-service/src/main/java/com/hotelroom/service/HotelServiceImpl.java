package com.hotelroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelroom.entity.Hotel;
import com.hotelroom.exception.ResourceNotFoundException;
import com.hotelroom.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	public HotelRepository hotelrepository;
	
	@Override
	public Hotel getHotelById(int hotelId) {
		Optional<Hotel> optionalhotel = hotelrepository.findById(hotelId);

		if (optionalhotel.isEmpty()) {
			throw new ResourceNotFoundException("Hotel Not found with id "+hotelId);

		}
		Hotel hotels = optionalhotel.get();
		return hotels;
	}

	@Override
	public Hotel createHotel(Hotel hotel) {
		hotelrepository.save(hotel);
		return hotel;
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

}
