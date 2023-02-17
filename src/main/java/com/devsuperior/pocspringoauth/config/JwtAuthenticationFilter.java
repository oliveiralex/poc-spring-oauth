package com.devsuperior.pocspringoauth.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.devsuperior.pocspringoauth.entities.User;
import com.devsuperior.pocspringoauth.services.JwtService;
import com.devsuperior.pocspringoauth.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/* Description:
 * This class is responsible for check JWT Token 
 * 
 * */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		/*
		 * Validation: If header is not null and header inits with Bearer, then get
		 * token and extract username by token
		 */
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
		}

		/*
		 * Check if user is stored in database and then update
		 * SecurityContextHolder
		 */
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = userService.loadUserByUsername(username);

			if (jwtService.isTokenValid(token, user)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
						user.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
