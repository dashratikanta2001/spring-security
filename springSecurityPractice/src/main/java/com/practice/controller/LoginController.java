package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

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
	
}
