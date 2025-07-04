package com.example.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bookings {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    @ManyToOne
    @JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	private Cars car;
	
	private LocalDate startDate;
	private LocalDate endDate;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Cars getCar() {
		return car;
	}
	public void setCar(Cars car) {
		this.car = car;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Bookings [id=" + id + ", user=" + user + ", car=" + car + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	public Bookings(User user, Cars car, LocalDate startDate, LocalDate endDate) {
		super();
		this.user = user;
		this.car = car;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Bookings() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
		
}
