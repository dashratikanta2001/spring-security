package com.ovms.service;

import com.ovms.dto.CustomerDto;
import com.ovms.response.CustomeResponse;

public interface CustomerService {

	CustomeResponse<?> save(CustomerDto customerDto);
	
	CustomeResponse<?> update(String email,CustomerDto customerDto);
	
	CustomeResponse<?> findByEmail(String email);

	CustomeResponse<?> showAllVehicles(String email);

	CustomeResponse<?> assignRole(Integer customerId, Integer roleId);
}
