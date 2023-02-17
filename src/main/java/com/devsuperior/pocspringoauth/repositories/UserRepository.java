package com.devsuperior.pocspringoauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.pocspringoauth.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	Optional<User> findByEmail(String email);
	User findByEmail(String email);
}

