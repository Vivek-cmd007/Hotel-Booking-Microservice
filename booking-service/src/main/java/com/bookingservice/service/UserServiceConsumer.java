package com.bookingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookingservice.model.User;


@FeignClient(name="USER-SERVICE")
public interface UserServiceConsumer {
	
	@GetMapping("/users/{id}")
	User getUserById(@PathVariable("id") int userId);

}
