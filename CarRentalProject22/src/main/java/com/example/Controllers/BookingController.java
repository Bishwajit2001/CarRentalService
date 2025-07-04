package com.example.Controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Repository.AuthenticationRepo;
import com.example.Repository.BookingRepo;
import com.example.Repository.DashboardRepo;
import com.example.entities.Bookings;
import com.example.entities.Cars;
import com.example.entities.User;
import com.example.services.EmailService;


@Controller
public class BookingController {
	
		@Autowired
		private EmailService emailService;
	
	  @Autowired
	    private BookingRepo bookingRepo;

	    @Autowired
	    private AuthenticationRepo authRepo;

	    @Autowired
	    private DashboardRepo carRepo;

	    @PostMapping("/confirmBooking")
	    public String bookedCar(@RequestParam("id") int carId,
	                            @RequestParam("startdate") LocalDate startDate,
	                            @RequestParam("days") int days,
	                            Principal principal,
	                            Model model) {

	        // Step 1: Get the logged-in username from Principal
	        String username = principal.getName();

	        // Step 2: Fetch the User entity from DB using username
	        User user = authRepo.findByUsername(username);

	        // Step 3: Fetch car details
	        Cars car = carRepo.findById(carId).orElse(null);

	        if (car == null || user == null) {
	            return "redirect:/dashboard";
	        }

	        //  Set booking details
	        LocalDate endDate = startDate.plusDays(days);
	        Bookings booking = new Bookings();
	        booking.setUser(user);
	        booking.setCar(car);
	        booking.setStartDate(startDate);
	        booking.setEndDate(endDate);

	        //Save booking
	        bookingRepo.save(booking);

	        // Send confirmation data to success page
	        model.addAttribute("bookedcar", car);
	        model.addAttribute("startdate", startDate);
	        model.addAttribute("enddate", endDate);
	        
	        String subject = " Booking Confirmed";

	        String body = "Hi " + user.getName() + ",\n\n"
	                    + "Your booking has been successfully confirmed. Here are the details:\n\n"
	                    + "🔹 Car Name: " + booking.getCar().getCar() + "\n"
	                    + "🔹 Model: " + booking.getCar().getModel() + "\n"
	                    + "🔹 Brand: " + booking.getCar().getBrand() + "\n"
	                    + "🔹 Price per Day: ₹" + booking.getCar().getPrice() + "\n"
	                    + "🔹 Start Date: " + booking.getStartDate() + "\n"
	                    + "🔹 End Date: " + booking.getEndDate() + "\n\n"
	                    + "Thank you for choosing Comprehensive Car Rental!\n"
	                    + "We wish you a smooth and enjoyable ride. 🚗💨\n\n"
	                    + "--\n"
	                    + "Comprehensive Car Rental Team";

	        emailService.sendBookingConfirmation(user.getUsername(), subject, body);
	        
	        return "BookingSuccess";
	    }


	}
