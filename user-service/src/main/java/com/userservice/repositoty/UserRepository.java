package com.userservice.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	

}
