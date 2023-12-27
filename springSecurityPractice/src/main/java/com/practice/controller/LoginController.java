package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.dao.SignupDao;
import com.practice.dto.SignupDto;

@Controller
public class LoginController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SignupDao signupDao;
	
	@GetMapping("/myCustomLogin")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout()
	{
		return "logout";
	}
	
	@GetMapping("/signup")
	public String signup(@ModelAttribute("signupDto") SignupDto signupDto)
	{
		return "signup";
	}
	
	@PostMapping("/process-signup")
	public String processSignup(SignupDto signupDto)
	{
		System.out.println(signupDto);
		
		signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		
		System.out.println(signupDto);
		//Writing my saving logic here.
		
		signupDao.saveUser(signupDto);
		
		
		return "redirect:/myCustomLogin";
	}
	
}
