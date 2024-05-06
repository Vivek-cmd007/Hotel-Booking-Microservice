package com.hotelroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelroom.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
