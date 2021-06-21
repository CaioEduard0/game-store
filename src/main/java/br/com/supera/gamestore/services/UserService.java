package br.com.supera.gamestore.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.supera.gamestore.entities.User;
import br.com.supera.gamestore.exceptions.RepeatedEmailException;
import br.com.supera.gamestore.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User with %s not found!", email)));
	}
	
	public void insertUser(User user) {
		Optional<User> email = userRepository.findByEmail(user.getEmail());
		if (email.isPresent()) {
			throw new RepeatedEmailException(user.getEmail());
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User with %s not found!", email)));
	}
}
