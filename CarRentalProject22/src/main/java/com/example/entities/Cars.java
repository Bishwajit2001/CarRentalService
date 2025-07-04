package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cars {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String car;
	private String brand;
	private int model;
	private double price;
	private String image;
	
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Cars() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cars(String car, int model, String brand, double price, String image) {
		super();
		this.car = car;
		this.model = model;
		this.brand = brand;
		this.price = price;
		this.image = image;
	}
	@Override
	public String toString() {
		return "Cars [id=" + id + ", car=" + car + ", model=" + model + ", brand=" + brand + ", price=" + price
				+ ", image=" + image + "]";
	}
	
	
	

}
