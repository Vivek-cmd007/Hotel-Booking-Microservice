package com.bookingservice.bookingtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookingservice.entity.BookingDetails;
import com.bookingservice.model.RoomDetails;
import com.bookingservice.model.User;
import com.bookingservice.repository.BookingDetailsRepository;
import com.bookingservice.service.BookingDetailsServiceImpl;
import com.bookingservice.service.HotelServiceConsumer;


@SpringBootTest
public class BookingTest {
	


	@InjectMocks
	private BookingDetailsServiceImpl bookingService;
	@Mock
	private BookingDetailsRepository bookingRepository;
	
	 @Mock
	 private HotelServiceConsumer hotelService;
	
	@Test
	public void testDeleteBookingDetails() {
		
		BookingDetails booking=new BookingDetails();
		booking.setBookingId(10);
		booking.setHotelId(111);
		booking.setUserId(2);
		booking.setRoomId(101);
		booking.setBookingDate(LocalDate.now());
		booking.setBookedFrom(LocalDate.now().plusDays(2));
		booking.setBookedTo(LocalDate.now().plusDays(4));
		booking.setNoOfAdults(2);
		booking.setNoOfChildren(0);
		booking.setAmount("2000");
		
		when(bookingRepository.findById(10)).thenReturn(Optional.of(booking));

		doNothing().when(bookingRepository).delete(booking);

		bookingService.deleteBookingDetails(10);

		
	}
	@Test
	public void testgetBookingDetailById() {
		
		BookingDetails  booking =new BookingDetails();
		booking.setBookingId(11);
		booking.setUserId(2);
		booking.setHotelId(112);
		booking.setRoomId(102);
		booking.setBookingDate(LocalDate.now());
		booking.setBookedFrom(LocalDate.now().plusDays(2));
		booking.setBookedTo(LocalDate.now().plusDays(4));
		booking.setNoOfAdults(3);
		booking.setNoOfChildren(2);
		booking.setAmount("6000");
		
		
	}
	@Test
	public void testGetAllBookingDetails() {
		BookingDetails booking=new BookingDetails();
		
		booking.setBookingId(13);
		booking.setHotelId(115);
		booking.setRoomId(103);
		booking.setUserId(3);
		booking.setBookingDate(LocalDate.now());
		booking.setBookedFrom(LocalDate.now().plusDays(2));
		booking.setBookedTo(LocalDate.now().plusDays(4));
		booking.setNoOfAdults(2);
		booking.setNoOfChildren(0);
		booking.setAmount("2500");
		
		BookingDetails booking1=new BookingDetails();
		booking.setBookingId(14);
		booking.setHotelId(116);
		booking.setUserId(4);
		booking.setRoomId(104);
		booking.setBookingDate(LocalDate.now());
		booking.setBookedFrom(LocalDate.now().plusDays(3));
		booking.setBookedTo(LocalDate.now().plusDays(4));
        booking.setNoOfAdults(1);
        booking.setNoOfChildren(0);
        booking.setAmount("1000");
        
        
        List<BookingDetails> bookingDetails = new ArrayList<>();
        bookingDetails.add(booking);
        bookingDetails.add(booking1);

		when(bookingRepository.findAll()).thenReturn(bookingDetails);

		List<BookingDetails> bookingList = bookingService.getAllBookingDetails();
		assertEquals(2, bookingList.size());

	}
	
	@Test
	public void testUpdateBookingDetails() {
		 BookingDetails booking = new BookingDetails();
	        booking.setBookingId(10);
	        booking.setRoomId(101);
	        booking.setUserId(1);
	        booking.setHotelId(1);
	        booking.setBookingDate(LocalDate.now());
			booking.setBookedFrom(LocalDate.now().plusDays(6));
			booking.setBookedTo(LocalDate.now().plusDays(7));
	        booking.setNoOfAdults(2);
	        booking.setNoOfChildren(1);
	        booking.setAmount("3000");
	        booking.setStatus("pending");

//	        Mockito.when(bookingRepository.save(Mockito.any(BookingDetails.class))).thenReturn(booking);
//
//	        BookingDetails updatedBooking = bookingService.updateBookingDetails(booking);
//	        // Verify and assert
//	        Mockito.verify(bookingRepository, Mockito.times(1)).save(booking);
//	        assertNotNull(updatedBooking);
//	        assertEquals(booking, updatedBooking);
	        
	        when(bookingRepository.findById(10)).thenReturn(Optional.of(booking));
			when(bookingRepository.save(booking)).thenReturn(booking);
			
			booking.setBookingId(10);
	        booking.setRoomId(101);
	        booking.setUserId(1);
	        booking.setHotelId(1);
	        booking.setBookingDate(LocalDate.now());
			booking.setBookedFrom(LocalDate.now().plusDays(6));
			booking.setBookedTo(LocalDate.now().plusDays(7));
	        booking.setNoOfAdults(2);
	        booking.setNoOfChildren(0);
	        booking.setAmount("5000");
	        booking.setStatus("Booked");
			
	        BookingDetails updatedBooking = bookingService.updateBookingDetails(booking);

			verify(bookingRepository, times(1)).findById(10);
			verify(bookingRepository, times(1)).save(booking);

			assertEquals(booking, updatedBooking);
			assertEquals("Booked", updatedBooking.getStatus());
			assertEquals("5000", updatedBooking.getAmount());
	    }
	
	

}
