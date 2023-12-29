package com.practice.dao;

import java.util.List;

import com.practice.dto.Customer;

public interface CustomerDao {
	
	List<Customer> findCustomerByUsername(String username);

}
