package com.userservice.controller;

import java.util.List;

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

import com.userservice.entity.User;
import com.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{Id}")
	public ResponseEntity<User> getUserById(@PathVariable("Id") int userId) {
		User user = userService.getUserById(userId);

		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@PostMapping("/save")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userService.createUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping("/all")
	public List<User> fetchAllUser() {

		List<User> users = userService.getAllUser();
		return users;
	}

	@DeleteMapping("/{userId}")
		public void deleteUser(@PathVariable("userId") int userId) {
			User user = userService.getUserById(userId);
			if (user != null) {
				userService.deleteUser(user);
			}
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> editProduct(@RequestBody User user) {
		userService.updateUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
	
}
	  //list of hotels user can see
//	  @GetMapping("/hotels")
//	  public List<Hotel> fetchAllHotelList(@RequestParam("hotelId") int hotelId ){
//		  List<Hotel> hotels=userService.getAllHotelList(hotelId);
//			return hotels;
//	  }

   
