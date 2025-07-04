package com.example.Controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Repository.AuthenticationRepo;
import com.example.Repository.BookingRepo;
import com.example.Repository.DashboardRepo;
import com.example.entities.Bookings;
import com.example.entities.Cars;
import com.example.entities.User;

import jakarta.annotation.PostConstruct;

@Controller
public class DashboardController {
	
	@Autowired
	private AuthenticationRepo userRepo;
	
	@Autowired
	private BookingRepo bookingRepo;
	
	
	
    @Autowired
    DashboardRepo repo;

    @PostConstruct
    public void addCars() {
        if (repo.existsByCar("Fortuner") || repo.existsByCar("Creta") || repo.existsByCar("Swift") || repo.existsByCar("Scorpio")) {
            return;
        }

        if (repo.count() == 0) {
            List<Cars> carList = new ArrayList<>();

            carList.add(new Cars("Fortuner", 2024, "Toyota", 5000, "fortuner2024.png"));
            carList.add(new Cars("Creta", 2024, "Hyundai", 3000, "creta.jpeg"));
            carList.add(new Cars("Swift", 2024, "Maruti", 2000, "swift2025.png"));
            carList.add(new Cars("Scorpio", 2024, "Mahindra", 4000, "scorpio.jpeg"));

            repo.saveAll(carList);
        }
    }

    @GetMapping("/dashboard")
    public String showCars(Model model,Principal principal) {
    	
    		String username = principal.getName();
    		User user= userRepo.findByUsername(username);
    		
        Iterable<Cars> allCars = repo.findAll();
        
        model.addAttribute("username",user.getName());
        model.addAttribute("allcars", allCars);
        return "Dashboard";
    }
    
    
    //booking history dikhane k liye
    
    @GetMapping("/user-dashboard")
    public String showBooking(Model model, Principal principal) {
    	String username= principal.getName();
    	
    	User user= userRepo.findByUsername(username);
    	
    	List<Bookings> bookings = bookingRepo.findByUser(user);
    	
    	model.addAttribute("bookings", bookings);
    	model.addAttribute("userName", user.getName());
    	
    	return "UserDashboard";    	
    }

    @GetMapping("/bookcar")
    public String bookCar(@RequestParam int id, Model model) {
        Optional<Cars> bookedCar = repo.findById(id);
        if (bookedCar.isPresent()) {
            model.addAttribute("bookedcar", bookedCar.get());
            model.addAttribute("date", LocalDate.now());
            return "BookingPage";  // <- exact match needed here
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/previewBooking")
    public String previewBooking(@RequestParam int id,
                                  @RequestParam int days,
                                  @RequestParam LocalDate startdate,
                                  Model model) {

        Optional<Cars> toBook = repo.findById(id);

        if (toBook.isPresent()) {
            Cars bookedcar = toBook.get();
            LocalDate enddate = startdate.plusDays(days);
            
            List<Bookings> overlapping = bookingRepo.findOverlappingBookings(id, startdate, enddate);
            if (!overlapping.isEmpty()) {
                model.addAttribute("error", "This car is already booked for the selected dates.");
                return "BookingPage"; // Dashboard p waps jao.
            }
            
            double totalPrice = bookedcar.getPrice() * days;
            

            model.addAttribute("startdate", startdate);
            model.addAttribute("enddate", enddate);
            model.addAttribute("bookedcar", bookedcar);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("days", days);

            return "ConfirmBooking";
        }

        return "redirect:/dashboard";
    }
    
    @GetMapping("/dashboard/filter")
    public String filterByPrice(@RequestParam String price, Model model, Principal principal) {
        List<Cars> filteredCars;
        
	String username= principal.getName();
    	
    	User user= userRepo.findByUsername(username);

        if (price.equals("all")) {
            filteredCars = (List<Cars>) repo.findAll();
        } else {
            int priceLimit = Integer.parseInt(price);
            filteredCars = repo.findByPriceLessThanEqual(priceLimit);
        }

        model.addAttribute("username",user.getName());
        model.addAttribute("allcars", filteredCars);
        return "Dashboard";
    }

}
