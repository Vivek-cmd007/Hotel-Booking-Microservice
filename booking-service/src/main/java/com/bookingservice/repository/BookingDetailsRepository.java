package com.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingservice.entity.BookingDetails;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails,Integer> {

}
