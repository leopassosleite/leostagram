package com.silentsoft.leostagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silentsoft.leostagram.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
}