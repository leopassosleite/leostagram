package com.silentsoft.leostagram.services;

import java.util.Set;

import com.silentsoft.leostagram.models.User;
import com.silentsoft.leostagram.models.UserRole;

public interface UserService {

	//cria usuário
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//busca usuário pelo username
	public User getUser(String username);

	public User getUserByEmail(String email);
	
	public void delete(Long userId);

}