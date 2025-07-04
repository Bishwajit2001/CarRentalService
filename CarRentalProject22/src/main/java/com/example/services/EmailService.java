package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendBookingConfirmation(String toEmail, String subject, String body) {
		SimpleMailMessage message= new SimpleMailMessage();
		
		message.setFrom("b.chy.2001@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
		
		System.out.println("Confirmation email sent to :" + toEmail);
	}
}