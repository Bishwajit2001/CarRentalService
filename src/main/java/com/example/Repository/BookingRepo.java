package com.example.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.entities.Bookings;

import java.time.LocalDate;
import java.util.List;
import com.example.entities.User;


public interface BookingRepo extends CrudRepository<Bookings, Integer> {
	List<Bookings> findByUser(User user);
	
	@Query("SELECT b FROM Bookings b WHERE b.car.id = :carId AND " +
		       "((:startDate BETWEEN b.startDate AND b.endDate) OR " +
		       "(:endDate BETWEEN b.startDate AND b.endDate) OR " +
		       "(b.startDate BETWEEN :startDate AND :endDate))")
		List<Bookings> findOverlappingBookings(@Param("carId") int carId,
		                                       @Param("startDate") LocalDate startDate,
		                                       @Param("endDate") LocalDate endDate);


}
