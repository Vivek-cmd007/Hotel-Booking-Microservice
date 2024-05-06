package com.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.userservice.entity.User;

@Service
public interface UserService {
	
	User getUserById(int userId);

	User createUser(User user);
	
	User updateUser(User user);
	
	void deleteUser(User user);
	
	List<User> getAllUser();
	
//	List<Hotel> getAllHotelList(int hotelId);


}
