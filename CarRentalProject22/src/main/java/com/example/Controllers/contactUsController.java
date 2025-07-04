package com.example.Controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Repository.AuthenticationRepo;
import com.example.Repository.contactUsRepo;
import com.example.entities.ContactUs;
import com.example.entities.User;

@Controller
public class contactUsController {
	
	@Autowired
	private contactUsRepo contactRepo;
	
	@Autowired
	private AuthenticationRepo authrepo;
	
	@GetMapping("/contact")
	public String showContact() {
		return "ContactUs";
	}
	
	@PostMapping("/contact/submit")
	public String submitContact(@RequestParam String message,
							    Model model, Principal principal ) {
		
		String email = principal.getName();
		User user= authrepo.findByUsername(email);
		
		ContactUs contact =new ContactUs(user, message, LocalDateTime.now());
		contactRepo.save(contact);
		
		model.addAttribute("contact",contact);
		return "ContactSuccess";
		
	}

}
