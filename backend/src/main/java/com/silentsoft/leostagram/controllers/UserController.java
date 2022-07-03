package com.silentsoft.leostagram.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silentsoft.leostagram.models.Role;
import com.silentsoft.leostagram.models.User;
import com.silentsoft.leostagram.models.UserRole;
import com.silentsoft.leostagram.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public User createUser(@RequestBody User user) throws Exception {

		user.setProfile("default.png");
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		roles.add(userRole);

		return this.userService.createUser(user, roles);
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
	@GetMapping("/getByEmail/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		return this.userService.getUserByEmail(email);
	}
	
	@DeleteMapping("{userId}")
	public void delete(@PathVariable("userId") Long userId) {
		this.userService.delete(userId);
	}
	
}