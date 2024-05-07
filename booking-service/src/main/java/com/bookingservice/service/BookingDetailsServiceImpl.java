package com.bookingservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookingservice.entity.BookingDetails;
import com.bookingservice.exception.DeadlinePassedException;
import com.bookingservice.exception.ResourceNotFoundException;
import com.bookingservice.model.BookingPayload;
import com.bookingservice.model.FullBookingResponse;
import com.bookingservice.model.Hotel;
import com.bookingservice.model.RoomDetails;
import com.bookingservice.model.User;
import com.bookingservice.repository.BookingDetailsRepository;


@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

	@Autowired
	private BookingDetailsRepository bookingrepository;

	@Autowired
	private HotelServiceConsumer hotelService;

	@Autowired
	private UserServiceConsumer userService;
	
	@Override
	public ResponseEntity<?> createBookingDetails(BookingDetails booking) {
	    System.out.println("Inside createBookingDetails Method:::");
	    
	    Hotel hotel = hotelService.getHotelById(booking.getHotelId());
	    if (hotel == null) {
	        throw new ResourceNotFoundException("Hotel not found with ID: " + booking.getHotelId());
	    }
	 
	    RoomDetails room = hotelService.getRoomDetailsById(booking.getRoomId());
	    if (room == null) {
	        throw new ResourceNotFoundException("Room not found with ID: " + booking.getRoomId());
	    }
	  
	    User user = userService.getUserById(booking.getUserId());
	    if (user == null) {
	        throw new ResourceNotFoundException("User not found with ID: " + booking.getUserId());
	    }

		if (room.isAvailable() == true) {

			room.setAvailable(false);
			System.out.println("Room Details after updating available ::: " + room.toString());

			hotelService.updateRoom(room);

			booking.setBookingDate(LocalDate.now());
			booking.setStatus("Booked");
			return new ResponseEntity<>(bookingrepository.save(booking), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Room is Already Booked.", HttpStatus.CONFLICT);
		}
	   
	}	


	@Override
	public BookingPayload getBookingDetailsById(int bookingId) {
		Optional<BookingDetails> optionalBooking = bookingrepository.findById(bookingId);

		if (optionalBooking.isEmpty()) {
			throw new ResourceNotFoundException("Booking Not Found :" + bookingId);

		}
		BookingDetails booking = optionalBooking.get();

		User user = userService.getUserById(booking.getUserId());
		RoomDetails room = hotelService.getRoomDetailsById(booking.getRoomId());
		Hotel hotel = hotelService.getHotelById(booking.getHotelId());

		BookingPayload payload = new BookingPayload();

		payload.setCustomer(user.getUserName());
		payload.setHotelName(hotel.getHotelName());
		payload.setRoomNo(room.getRoomNo());
		payload.setRoomType(room.getRoomType());
		payload.setMobile(user.getMobile());
		payload.setHotelPhone1(hotel.getHotelPhone1());
		payload.setAmount(booking.getAmount());

		return payload;

	}

	@Override
	public List<BookingDetails> getAllBookingDetails() {
		return bookingrepository.findAll();

	}

	@Override
	public void deleteBookingDetails(int bookingId) {
		Optional<BookingDetails> optionalbooking = bookingrepository.findById(bookingId);
		if (optionalbooking.isEmpty()) {
			throw new ResourceNotFoundException("Booking Not Found with Id" + bookingId);
		}
		BookingDetails bookings = optionalbooking.get();
		bookingrepository.delete(bookings);
		

	}

	@Override
	public BookingDetails updateBookingDetails(BookingDetails bookingDetails) {

		Optional<BookingDetails> booking = bookingrepository.findById(bookingDetails.getBookingId());
		if (booking.isEmpty()) {
			throw new ResourceNotFoundException("Booking Details Not Found with Id" + bookingDetails.getAmount());
		}
		return bookingrepository.save(bookingDetails);
	}

	@Override
	public FullBookingResponse getFullBookingDetails(int bookingId) {

		Optional<BookingDetails> optionalBooking = bookingrepository.findById(bookingId);

		if (optionalBooking.isEmpty()) {
			throw new ResourceNotFoundException("Booking Not Found" + bookingId);
		}

		BookingDetails booking = optionalBooking.get();

		User userDetails = userService.getUserById(booking.getUserId());
		System.out.println("User Details ::: " + userDetails.toString());

		RoomDetails roomDetails = hotelService.getRoomDetailsById(booking.getRoomId());
		System.out.println("Room Details ::: " + roomDetails.toString());

		FullBookingResponse bookingResponse = new FullBookingResponse();
		bookingResponse.setBookingId(bookingId);
		bookingResponse.setStatus(booking.getStatus());
		bookingResponse.setHotelId(booking.getHotelId());
		bookingResponse.setRoomDetails(roomDetails);
		bookingResponse.setCustomer(userDetails);
		bookingResponse.setBookedFrom(booking.getBookedFrom());
		bookingResponse.setBookedTo(booking.getBookedTo());
		bookingResponse.setNoOfAdults(booking.getNoOfAdults());
		bookingResponse.setNoOfChildren(booking.getNoOfChildren());
		bookingResponse.setAmount(booking.getAmount());

		return bookingResponse;
	}

	@Override
	public ResponseEntity<?> getFullBookingDetailsOfAllIds() {

		FullBookingResponse fullBookingResponse = null;
		List<FullBookingResponse> fullBookingResponses = new ArrayList<FullBookingResponse>();
		List<BookingDetails> bookingDetails = bookingrepository.findAll();

		for (BookingDetails bookingDetail : bookingDetails) {
			fullBookingResponse = getFullBookingDetails(bookingDetail.getBookingId());
			fullBookingResponses.add(fullBookingResponse);
		}

		return new ResponseEntity<>(fullBookingResponses, HttpStatus.OK);
	}

	@Override
	public BookingDetails cancelBooking(int bookingId) {
		Optional<BookingDetails> optionalbooking = bookingrepository.findById(bookingId);
		if (optionalbooking.isEmpty()) {
			throw new ResourceNotFoundException("Booking Details Not found with id " + bookingId);
		}
		BookingDetails booking = optionalbooking.get();
		if (booking.getBookingDate().isAfter(booking.getBookedFrom().minusDays(1))) {
			throw new DeadlinePassedException("Cancellation deadline has passed");
		}
		booking.setStatus("Canceled");
		;
		RoomDetails room = hotelService.getRoomDetailsById(booking.getRoomId());
		room.setAvailable(true);
		hotelService.updateRoom(room);

		return bookingrepository.save(booking);
	}

}
