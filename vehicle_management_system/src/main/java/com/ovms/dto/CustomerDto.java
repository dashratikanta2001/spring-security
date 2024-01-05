package com.ovms.dto;

import java.util.List;

import javax.validation.constraints.Size;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ovms.entity.Customer;

@JsonInclude(Include.NON_NULL)
public class CustomerDto {

	private Integer id;

	private String name;

	private String email;

	@Size(min = 10, max = 13, message = "Phone number must be minimum 10 digits")
	private String phoneNo;

	private String address;

	private List<VehicleDto> vehicles;

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

	public List<VehicleDto> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<VehicleDto> vehicles) {
		this.vehicles = vehicles;
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

//	public  static CustomerDto convertToDto(Customer customer) {
//		
//		CustomerDto customerDto = new CustomerDto();
//		
//		if(customer!=null) {
//			customerDto.setId(customer.getId());
//			customerDto.setName(customer.getName());
//			customerDto.setEmail(customer.getEmail());
//			customerDto.setAddress(customer.getAddress());
//			customerDto.setPhoneNo(customer.getPhoneNo());
//			
//			return customerDto;
//		}
//		return null;
//		
//	}
//	
//	public  static CustomerDto convertToDto(Customer customer, List<VehicleDto> vehicles) {
//		
//		CustomerDto customerDto = new CustomerDto();
//		
//		if(customer!=null) {
//			customerDto.setId(customer.getId());
//			customerDto.setName(customer.getName());
//			customerDto.setEmail(customer.getEmail());
//			customerDto.setAddress(customer.getAddress());
//			customerDto.setPhoneNo(customer.getPhoneNo());
//			customerDto.setVehicles(vehicles);
//			
//			return customerDto;
//		}
//		return null;
//		
//	}

}
