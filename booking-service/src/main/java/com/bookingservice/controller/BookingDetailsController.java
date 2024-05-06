package com.bookingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookingservice.entity.BookingDetails;
import com.bookingservice.model.FullBookingResponse;
import com.bookingservice.service.BookingDetailsService;

@RestController
@RequestMapping("/bookings")
public class BookingDetailsController {

  @Autowired
  BookingDetailsService bookingDetailsService;
  
  @PostMapping("/save")
  public ResponseEntity<BookingDetails> createBookingDetails(@RequestBody BookingDetails bookingDetails) {
    return new ResponseEntity<BookingDetails>(bookingDetailsService.createBookingDetails(bookingDetails),HttpStatus.CREATED);
   
  }

  @PutMapping("/update")
  public ResponseEntity<BookingDetails> updateBooking(@RequestBody BookingDetails bookingDetails){
	  return new ResponseEntity<BookingDetails>(bookingDetailsService.updateBookingDetails( bookingDetails),HttpStatus.OK);
  }

//  @GetMapping("/{Id}")
//  public ResponseEntity<BookingPayload> getBookingDetailsById(@PathVariable("Id") int bookingId) {
//    BookingPayload bookingDetails = bookingDetailsService.getBookingDetailsById(bookingId);
//  
//      return new ResponseEntity<>(bookingDetails,HttpStatus.OK);
//    }
//
//  @GetMapping("/getAll")
//  public ResponseEntity<List<BookingDetails>> getAllBooking(){
//	  return new ResponseEntity<List<BookingDetails>>(bookingDetailsService.getAllBookingDetails(),HttpStatus.OK);
//  }
  
  @GetMapping("/getFullBookingDetails/{bookingId}")
  public FullBookingResponse getFullBookingDetails(@PathVariable("bookingId") int bookingId) {
	  return bookingDetailsService.getFullBookingDetails(bookingId);
  }
  
  
  @GetMapping("/getAllBookingDetails")
  public ResponseEntity<?> getFullBookingDetailsOfAllIds(){
	  return bookingDetailsService.getFullBookingDetailsOfAllIds();
  }
  
  @PutMapping("cancel/{id}")
	public ResponseEntity<BookingDetails> cancelBooking(@PathVariable("id") int bookingId){
		return new ResponseEntity<BookingDetails>(bookingDetailsService.cancelBooking(bookingId),HttpStatus.OK);
	}
  
  @DeleteMapping("/{Id}")
  public ResponseEntity<Void> deleteBookingDetails(@PathVariable("Id") int bookingId) {
     bookingDetailsService.deleteBookingDetails(bookingId);
  
     ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;

    }
  
 


}
