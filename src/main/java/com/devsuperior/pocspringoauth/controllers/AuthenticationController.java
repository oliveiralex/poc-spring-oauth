package com.devsuperior.pocspringoauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.pocspringoauth.dto.AuthRequestDTO;
import com.devsuperior.pocspringoauth.dto.AuthResponseDTO;
import com.devsuperior.pocspringoauth.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping
	public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request) {
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
}
