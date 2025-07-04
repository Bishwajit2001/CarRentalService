package com.example.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Cars;

@Repository
public interface DashboardRepo extends CrudRepository<Cars, Integer> {
	boolean existsByCar(String car);
	
//	filter method use krne k liye
	List<Cars> findByPriceLessThanEqual(int price);
	
	

}
