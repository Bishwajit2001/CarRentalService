package com.example.Controllers;

import java.util.ArrayList;

import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.AuthenticationRepo;
import com.example.entities.User;
import com.example.services.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationRepo repo;
	
	@GetMapping("/")
	public String AuthenticationPage() {
		return "AuthenticationPage";
	}
	
	@GetMapping("/login")
	public String Login() {
		return "Login";
	}
	
//	@PostMapping("/login")
//	public String viewLogin(@RequestParam("username") String username,
//	                        @RequestParam("password") String password,
//	                        Model model) {
//
//	    System.out.println("== Inside Web Login ==");
//
//	    User user = repo.findByUsernameAndPassword(username, password);
//
//	    if (user != null) {
//	        return "redirect:/dashboard";
//	    } else {
//	        model.addAttribute("error", "Invalid credentials entered");
//	        return "Login";
//	    }
//	}


	
	@GetMapping("/signUp")
	public String viewSignUp() {
		return "Signup";
	}
		
	
	@PostMapping("/signUp")
	public String signUp(
	    @RequestParam String name,
	    @RequestParam String username,
	    @RequestParam String password,
	    @RequestParam long phoneNumber
	) {
	    System.out.println("======> Inside SignUp Handler");

	    User user = new User();
	    user.setName(name);
	    user.setUsername(username);
	    user.setPassword(passwordEncoder.encode(password));
	    user.setPhoneNumber(phoneNumber);

	    try {
	        repo.save(user);
	        System.out.println("Saved successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return "Login";
	}
	
	
	@GetMapping("/userdetails")
	@ResponseBody
	public Iterable<User> viewAllUser() {
		return userService.getAllUser(); 
	}

}
