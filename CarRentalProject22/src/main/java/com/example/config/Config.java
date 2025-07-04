package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

import com.example.services.CustomUserDetailsService;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration			
@EnableWebSecurity
public class Config {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		return
			
				http.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(req->req.requestMatchers("/","/signUp","/login").permitAll())
		.authorizeHttpRequests(req->req.anyRequest().authenticated())
		.formLogin(login -> login
			    .loginPage("/login")
			    .loginProcessingUrl("/login") // this endpoint will be POSTed
			    .defaultSuccessUrl("/dashboard", true)
			    .permitAll())
//		.httpBasic(withDefaults())
		.build();
	}
	
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        
        builder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());

        return builder.build();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
