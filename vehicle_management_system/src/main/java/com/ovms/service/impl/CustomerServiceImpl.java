package com.ovms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ovms.dao.CustomerDao;
import com.ovms.dto.CustomerDto;
import com.ovms.entity.Customer;
import com.ovms.response.CustomeResponse;
import com.ovms.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public CustomeResponse<?> save(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		
		if (customerDao.findByEmail(customerDto.getEmail()) == null) {
		Customer customer = customerDao.save(dtoToCustomer(customerDto));
		return new CustomeResponse<>(CustomerToDto(customer), HttpStatus.OK.value(), "Customer added.");
		}
		
		return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "Customer not added.");
	}	
	
	@Override
	public CustomeResponse<?> findByEmail(String email) {
		// TODO Auto-generated method stub
		
		Customer customer = customerDao.findByEmail(email);
		if (customer== null) {
			return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "Email not found.");
		}
		return new CustomeResponse<>(CustomerToDto(customer), HttpStatus.OK.value(), "Customer found");
	}
	
	@Override
	public CustomeResponse<?> update(String email, CustomerDto customerDto) {
		// TODO Auto-generated method stub
		Customer customer = customerDao.findByEmail(email);
		if (customer== null) {
			return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(),"Email not found");
		}
		
		customer.setName(customerDto.getName());
		customer.setAddress(customerDto.getAddress());
		customer.setPhoneNo(customerDto.getPhoneNo());
		
		System.out.println("User id = "+customer.getId());
		
		customerDao.save(customer);
		System.out.println("2 = User id = "+customer.getId());
		
		return new CustomeResponse<>(CustomerToDto(customer), HttpStatus.OK.value(),"Data updated.");
	}
	
	
	public Customer dtoToCustomer(CustomerDto customerDto)
	{
		Customer customer = new Customer(customerDto.getName(), customerDto.getEmail(), customerDto.getPhoneNo(), customerDto.getAddress());
		
		return customer;
	}
	public CustomerDto CustomerToDto(Customer customer)
	{
		CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhoneNo(), customer.getAddress());
		
		return customerDto;
	}




}
