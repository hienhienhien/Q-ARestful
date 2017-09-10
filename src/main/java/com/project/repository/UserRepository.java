package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	//find user by username 	
	public User findByUsername(String username);

	//find user by user id 
	public User findByUserId(Long userId);



}
