package com.example.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Repository.AuthenticationRepo;
import com.example.entities.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AuthenticationRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user ==null) {
			throw new UsernameNotFoundException("user not found");
		
		}
		
		return new org.springframework.security.core.userdetails.User(
		        user.getUsername(), // matches DB field
		        user.getPassword(), // already encoded
		        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
		    );
		
	}

	

}
