package com.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.entity.User;
import com.userservice.exception.ResourceNotFoundException;
import com.userservice.repositoty.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		Optional<User> optionaluser = userRepository.findById(user.getUserId());
		if (optionaluser.isEmpty()) {
			throw new ResourceNotFoundException("User Not found with id "+user.getUserId());
		}
		userRepository.save(user);
		return user;
	
	}

	@Override
	public void deleteUser(User user) {
		Optional<User> optionalproduct = userRepository.findById(user.getUserId());
		if (optionalproduct.isEmpty()) {
			throw new ResourceNotFoundException("User Not found with id "+user.getUserId());

		}
		User users =optionalproduct.get();
		userRepository.delete(users);
		
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> optionalproduct = userRepository.findById(userId);

		if (optionalproduct.isEmpty()) {
			throw new ResourceNotFoundException("User Not found with id "+userId);

		}
		User user = optionalproduct.get();
		return user;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users= userRepository.findAll();
		return users;
	}

//	@Override
//	public List<Hotel> getAllHotelList(int hotelId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
