package com.example.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.User;

public interface AuthenticationRepo extends CrudRepository<User, Integer>{
	
	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
}
