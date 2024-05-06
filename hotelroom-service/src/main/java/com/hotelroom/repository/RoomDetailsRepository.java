package com.hotelroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelroom.entity.RoomDetails;

public interface RoomDetailsRepository extends JpaRepository<RoomDetails, Integer> {

}
