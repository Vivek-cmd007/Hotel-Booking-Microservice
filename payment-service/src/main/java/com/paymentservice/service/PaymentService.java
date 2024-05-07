package com.paymentservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.paymentservice.entity.Payment;


@Service
public interface PaymentService {

	Payment getPaymentById(int paymentId);

	ResponseEntity<?> createPayment( Payment payment);
	
	List<Payment> getAllPayment();
}
