package com.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.dao.CustomerDao;
import com.practice.dto.Customer;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		// First check if the user with username is present or not.
		
		List<Customer> customers = customerDao.findCustomerByUsername(username);
		
		if (customers.isEmpty()) {
			throw new UsernameNotFoundException(username+ " not found.");
		}
		
		Customer customer = customers.get(0);
		
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(customer.getRoles()));
		
		
		return new User(customer.getName(), customer.getPassword(), authorities);
	}

}
