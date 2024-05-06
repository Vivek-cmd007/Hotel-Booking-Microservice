package com.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_TBL")
public class User {
	
	  @Id
	  @Column(name="user_id")
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  private int userId;
	  
	  @NotBlank(message = "Name is mandatory")
	  private String userName;

	  @NotBlank(message = "Email is mandatory")
	  private String email;

	  @NotBlank(message = "Password is mandatory")
	  private String password;

	  private String role;

	 @NotNull
	  private String mobile;

	  @NotNull(message = "address is mandatory")
	  private String address;

}
