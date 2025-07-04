package com.example.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.ContactUs;

public interface contactUsRepo extends CrudRepository<ContactUs, Integer> {

}
