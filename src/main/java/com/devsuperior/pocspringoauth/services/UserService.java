package com.devsuperior.pocspringoauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.pocspringoauth.entities.User;
import com.devsuperior.pocspringoauth.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private static Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			LOG.error("[LOG] user not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		LOG.info("[LOG] user found: " + user.toString());
		return user;
	}

}
