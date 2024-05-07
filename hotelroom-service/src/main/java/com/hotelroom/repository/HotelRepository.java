package com.hotelroom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotelroom.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	
	@Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.roomDetails WHERE h.hotelId = :hotelId")
    Optional<Hotel> findHotelWithRoomDetailsById(@Param("hotelId") int hotelId);

}
