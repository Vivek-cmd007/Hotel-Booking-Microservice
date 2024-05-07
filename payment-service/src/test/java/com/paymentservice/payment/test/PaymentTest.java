package com.paymentservice.payment.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.paymentservice.entity.Payment;
import com.paymentservice.model.BookingResponse;
import com.paymentservice.repository.PaymentRepository;
import com.paymentservice.service.BookingServiceConsumer;
import com.paymentservice.service.PaymentService;
import com.paymentservice.service.PaymentServiceImpl;

@SpringBootTest
public class PaymentTest {
	 @Mock
	    private PaymentRepository paymentRepository;

	    @Mock
	    private BookingServiceConsumer bookingService;

	    @InjectMocks
	    private PaymentServiceImpl paymentService;

	    @Test
	    public void testGetPaymentById() {
	    	
	        PaymentService paymentService = mock(PaymentService.class);
	        
	        Payment expectedPayment = new Payment();
	        expectedPayment.setPaymentId(1);
	        
	        when(paymentService.getPaymentById(1)).thenReturn(expectedPayment);
	       
	        Payment retrievedPayment = paymentService.getPaymentById(1);

	        
	        assertEquals(1, retrievedPayment.getPaymentId());
	    }
	    
	    @Test
	    public void testCreatePayment() {
	        // Prepare test data
	        Payment payment = new Payment();
	        payment.setPaymentId(1);
	        payment.setBookingId(1);
	        payment.setPayAmount("2000");

	        BookingResponse booking = new BookingResponse();
	        booking.setBookingId(1);
	        booking.setAmount("2000");

	        when(bookingService.getFullBookingDetails(payment.getBookingId())).thenReturn(booking);

	        when(paymentRepository.save(payment)).thenReturn(payment);

	        ResponseEntity<?> savedPayment = paymentService.createPayment(payment);

	        verify(bookingService, times(1)).getFullBookingDetails(payment.getBookingId());
	        verify(paymentRepository, times(1)).save(payment);
	        assertEquals(payment, savedPayment);
	    }
	
	    
	    @Test
	    public void testGetAllPayment() {
	        List<Payment> payments = new ArrayList<>();
	        Payment payment1 = new Payment();
	        payment1.setPaymentId(1);
	        payments.add(payment1);
	        Payment payment2 = new Payment();
	        payment2.setPaymentId(2);
	        payments.add(payment2);
	        when(paymentService.getAllPayment()).thenReturn(payments);
	        
	        List<Payment> retrievedPayments = paymentService.getAllPayment();
	        
	        assertEquals(2, retrievedPayments.size());
	        assertEquals(1, retrievedPayments.get(0).getPaymentId());
	        assertEquals(2, retrievedPayments.get(1).getPaymentId());
	    }
	  
	    @Test
	    public void testGetAllPaymentsDetails() {
	        // Prepare test data
	        Payment payment1 = new Payment();
	        payment1.setPaymentId(1);
	        payment1.setBookingId(3);
	        payment1.setPayAmount("3000");
	        Payment payment2 = new Payment();
	        payment2.setPaymentId(2);
	        payment2.setBookingId(4);
	        payment2.setPayAmount("4000");
	        List<Payment> payments =  new ArrayList<>();
	        payments.add(payment1);
	        payments.add(payment2);
	        
	        
	        when(paymentRepository.findAll()).thenReturn(payments);
	        
	        List<Payment> retrievedPayments = paymentService.getAllPayment();
	        verify(paymentRepository, times(1)).findAll();
	        
	        assertEquals(payments.size(), retrievedPayments.size());
	        assertEquals(payments, retrievedPayments);
	        
	    }
		
	}