package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	@ResponseBody
	@GetMapping("/helloWorld") //dont secure
	public String helloWorld()
	{
		return "hello-world";
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
}
