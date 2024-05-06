package com.paymentservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentservice.entity.Payment;
import com.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @GetMapping("/{paymentId}")
  public ResponseEntity<Payment> getPayment(@PathVariable("paymentId") int paymentId) {
	  Payment user = paymentService.getPaymentById(paymentId);

		return new ResponseEntity<>(user, HttpStatus.OK);

  }

  @PostMapping
  public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
//    Payment savedPayment = paymentService.createPayment(payment);
//    return ResponseEntity.ok(savedPayment);
	  return paymentService.createPayment(payment);
  }

}
