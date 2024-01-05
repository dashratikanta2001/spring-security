package com.ovms.dao;

import com.ovms.entity.Customer;

public interface CustomerDao {

	Customer findByEmail(String email);
	
	Customer save(Customer customer);

	Customer findById(Integer u_id);
}
