package com.devsuperior.pocspringoauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.pocspringoauth.dto.AuthRequestDTO;
import com.devsuperior.pocspringoauth.dto.AuthResponseDTO;
import com.devsuperior.pocspringoauth.entities.User;
import com.devsuperior.pocspringoauth.repositories.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepository userRepository;

	public AuthResponseDTO authenticate(AuthRequestDTO request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		 if (authentication.isAuthenticated()) {
	            User user = userRepository.findByEmail(request.getEmail());
			 	String token = jwtService.generateToken(user);
			 	return new AuthResponseDTO(token);
	        } else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }
	}
}
