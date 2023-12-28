package com.practice.controller;

import java.security.Principal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.dao.SignupDao;
import com.practice.dto.ChangePasswordDto;
import com.practice.dto.SignupDto;

@Controller
public class LoginController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SignupDao signupDao;
		
	@Autowired
	private JdbcUserDetailsManager userDetailsManager;
	
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
				
		
		UserDetails user = User.withUsername(signupDto.getUsername()).password(signupDto.getPassword()).authorities("Coder").build();
		
		
		userDetailsManager.createUser(user);
		
		
//		signupDao.saveUser(signupDto);
		
		
		return "redirect:/myCustomLogin";
	}
	
	
	@GetMapping("/changePassword")
	public String changePassword(Model model)
	{
		
		model.addAttribute("password-chng",new ChangePasswordDto());
		return "change-password";
	}
	
//	@ResponseBody
	@PostMapping("/save-password")
	public String savePassword(Principal principal, ChangePasswordDto changePasswordDto)
	{
		System.out.println(changePasswordDto);
		//Check wheather the new password and confirm password matched.
		
		if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getconfirmPassword())) {
			return "redirect:/changePassword?passwordNotMatch";
		}
		
		
		//Check if the old password is valid or not.
		
		UserDetails user = userDetailsManager.loadUserByUsername(principal.getName());
		
		boolean matches = passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword());
		
		if (matches) {
			userDetailsManager.changePassword(changePasswordDto.getOldPassword(),passwordEncoder.encode(changePasswordDto.getNewPassword()));
			System.out.println("Password changed.");
			return "redirect:/";
		}			
		
		
		
		
		//Write the logix to save the new password in the db.
		
		
		return "redirect:/changePassword?invalidPassword";
	}
	
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("username") String username)
	{
		
		userDetailsManager.deleteUser(username);
		
		System.out.println("User deleted: "+username);
		return "redirect:/myCustomLogin";
	}
}
