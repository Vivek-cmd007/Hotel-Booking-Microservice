package com.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
