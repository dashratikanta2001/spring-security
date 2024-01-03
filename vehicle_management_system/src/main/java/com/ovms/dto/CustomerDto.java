package com.ovms.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ovms.entity.Customer;

public class CustomerDto {
	
	

	private Integer id;

	private String name;

	private String email;
	
	private String phoneNo;

	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDto(Integer id, String name, String email, String phoneNo, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
	}
	
	
	public  static CustomerDto convertToDto(Customer customer) {
		
		CustomerDto customerDto = new CustomerDto();
		
		if(customer!=null) {
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setEmail(customer.getEmail());
			customerDto.setAddress(customer.getAddress());
			customerDto.setPhoneNo(customer.getPhoneNo());
			
			return customerDto;
		}
		return null;
		
	}
	
	
	
	
}
