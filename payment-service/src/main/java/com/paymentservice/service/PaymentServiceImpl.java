package com.paymentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.paymentservice.entity.Payment;
import com.paymentservice.exception.ResourceNotFoundException;
import com.paymentservice.model.BookingResponse;
import com.paymentservice.repository.PaymentRepository;



@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	BookingServiceConsumer bookingService;
	
	@Autowired
	PaymentRepository paymentService;

	@Override
	public Payment getPaymentById(int paymentId) {
		Optional<Payment> optionalproduct = paymentService.findById(paymentId);

		if (optionalproduct.isEmpty()) {
			throw new ResourceNotFoundException("User Not found with id "+paymentId);

		}
		Payment payment = optionalproduct.get();
		return payment;
	}
	
	@Override
	public ResponseEntity<?> createPayment(Payment payment) {

	    BookingResponse booking = bookingService.getFullBookingDetails(payment.getBookingId());

	    // if the booking is canceled
	    if (booking.getStatus().equalsIgnoreCase("Canceled")) {
	        return new ResponseEntity<>("Cannot make payment for a canceled booking", HttpStatus.CONFLICT);
	    }

	    // if payment already exists for the booking ID
	    boolean isPaymentExists = paymentService.existsByBookingId(payment.getBookingId());
	    if (isPaymentExists) {
	        return new ResponseEntity<>("Payment already exists for the booking", HttpStatus.CONFLICT);
	    }

	    System.out.println("Payment Amount: [" + payment.getPayAmount() + "]");
	    System.out.println("Booking Amount: [" + booking.getAmount() + "]");

	    if (payment.getPayAmount().equalsIgnoreCase((booking.getAmount()))) {

	        Payment processedPayment = paymentService.save(payment);

	        return new ResponseEntity<>(processedPayment, HttpStatus.OK);
	    } else {

	        return new ResponseEntity<>("Payment Declined", HttpStatus.CONFLICT);
	    }
	}


	@Override
	public List<Payment> getAllPayment() {
		List<Payment> payments= paymentService.findAll();
		return payments;
	}

}
