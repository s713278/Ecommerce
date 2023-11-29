package com.srtech.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.srtech.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * This is to demonstrate to fetch the user name and pwd from own tables rather
 * than out of box spring security framework tables.
 */
@Slf4j
public class MySQLUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.debug("Loading user information {}", email);
		Optional<UserEntity> optional = userRepository.findByEmail(email);
		UserEntity user = null;
		String userName, pwd = null;
		List<GrantedAuthority> authorities = null;
		if (optional.isPresent()) {
			UserEntity profile = optional.get();
			userName = profile.getEmail();
			pwd = profile.getPwd();
			authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("admin"));
		} else {
			throw new UsernameNotFoundException("No user found with email " + email);
		}
		return new User(userName, pwd, authorities);

	}

}
