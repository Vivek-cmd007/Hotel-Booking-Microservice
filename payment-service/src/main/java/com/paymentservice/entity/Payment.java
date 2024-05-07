package com.paymentservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PAYMENT")
public class Payment {
	
	  @Id
	  @Column(name="payment_id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int paymentId;
	  
	  @NotNull(message = "Enter a valid  Id")
	  private int transactionId;
	  
	  @Positive(message="Only Positive values Allowed")
	  private String payAmount;	
	  
	  @NotNull(message = "Enter a valid  Id")
	  private int bookingId;
	  
	  @Positive(message="Only Positive values Allowed")
	  private String amount;

	  private boolean isPaymentDone;
}
