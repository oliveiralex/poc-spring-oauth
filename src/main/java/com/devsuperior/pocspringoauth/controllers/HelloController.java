package com.devsuperior.pocspringoauth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

	@GetMapping()
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Hello world");
	}
	
	@GetMapping("/private")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> helloPrivate() {
		return ResponseEntity.ok("Hello world private - Only admins");
	}
} 
