package com.generation.backend.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.backend.entity.User;
import com.generation.backend.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<User> users = Optional.ofNullable(userRepository.findByFirstName(userName));

		if (users.isPresent())
			return new UserDetailsImpl(users.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	}
}
