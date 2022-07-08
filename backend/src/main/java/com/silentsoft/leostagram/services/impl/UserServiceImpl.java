package com.silentsoft.leostagram.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silentsoft.leostagram.models.User;
import com.silentsoft.leostagram.models.UserRole;
import com.silentsoft.leostagram.repositories.RoleRepository;
import com.silentsoft.leostagram.repositories.UserRepository;
import com.silentsoft.leostagram.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	// cria usuário
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User local = this.userRepository.findByEmail(user.getEmail());
		User localUsername = this.userRepository.findByUsername(user.getUsername());
		if (local != null && localUsername != null) {
			System.out.println("O Usuário Já Existe !!");
			throw new Exception("O Usuário Já Está Salvo No Banco !!");
		} else {
			// salva usuário
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}

			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
			localUsername = this.userRepository.save(user);
		}
		return local;
	}

	// Busca usuário pelo username
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public User getUserByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public void delete(Long userId) {
		this.userRepository.deleteById(userId);
	}
}