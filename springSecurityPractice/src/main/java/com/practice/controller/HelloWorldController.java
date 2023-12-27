package com.practice.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	
	//principal means username.

//	@ResponseBody
	@GetMapping("/") //dont secure
	public String helloWorld(Principal principal ,Authentication auth, Model model)
	{
		//Fetching username
		String username = principal.getName();
//		System.out.println("Logged in user = "+username);
		
		//Fetching the roles/authorities
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
//		System.out.println(authorities);
		
		model.addAttribute("username", username);
		model.addAttribute("roles", authorities);
		
		return "home-page";
	}
	
	
	@ResponseBody
	@GetMapping("/hello")  //secure
	public String hello()
	{
		return "hello from Ratikanta";
	}
	
	@ResponseBody
	@GetMapping("/bye")  //secure
	public String bye()
	{
		return "bye bye !!!!";
	}
	
	@GetMapping("/trainer")
	public String showTrainerDashboard()
	{
		return "trainer-dashboard";
	}
	
	@GetMapping("/coder")
	public String showCoderDashboard()
	{
		return "coder-dashboard";
	}
	
	
	@GetMapping("/accessDenied")
	public String error()
	{
		return "access-denied";
	}
}
