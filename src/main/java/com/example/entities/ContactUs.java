package com.example.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ContactUs {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;
	
	@Column(columnDefinition = "TEXT")
	private String message;
	
	private LocalDateTime localdatetime;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getLocaldatetime() {
		return localdatetime;
	}

	public void setLocaldatetime(LocalDateTime localdatetime) {
		this.localdatetime = localdatetime;
	}

	@Override
	public String toString() {
		return "ContactUs [id=" + id + ", user=" + user + ", message=" + message + ", localdatetime=" + localdatetime
				+ "]";
	}

	public ContactUs(User user, String message, LocalDateTime localdatetime) {
		super();
		this.user = user;
		this.message = message;
		this.localdatetime = localdatetime;
	}

	public ContactUs() {
		super();
		// TODO Auto-generated constructor stub
	}
	}

	