package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.AuthenticationRepo;
import com.example.entities.User;


@Service
public class UserService {

	@Autowired
	private AuthenticationRepo repo;
	
	public Iterable<User> getAllUser(){	
		return repo.findAll();
	
	}
	
	public Iterable<User> getById(List<Integer> userId) {
		return repo.findAllById(userId);
	}
}
