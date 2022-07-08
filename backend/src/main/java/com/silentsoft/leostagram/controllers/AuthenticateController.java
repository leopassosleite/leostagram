package com.silentsoft.leostagram.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.silentsoft.leostagram.config.JwtUtils;
import com.silentsoft.leostagram.helpers.UserNotFoundExcpetion;
import com.silentsoft.leostagram.models.JwtRequest;
import com.silentsoft.leostagram.models.JwtResponse;
import com.silentsoft.leostagram.models.User;
import com.silentsoft.leostagram.services.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		} catch (UserNotFoundExcpetion e) {
			e.printStackTrace();
			throw new Exception("Usuário não encontrado");
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			throw new Exception("USER DISABLED" + e.getMessage());
		} catch (BadCredentialsException e) {
			throw new Exception("Email ou senha não encontrados " + e.getMessage());
		}
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal){
		return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
	}
}
