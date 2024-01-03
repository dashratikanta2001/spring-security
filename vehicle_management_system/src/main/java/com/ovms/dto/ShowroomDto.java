package com.ovms.dto;

import java.util.List;

import com.ovms.entity.Brands;
import com.ovms.entity.Vehicle;

public class ShowroomDto {

	private int id;

	private String name;

	private String address;

	private String phoneNo;

	private String email;
	
	private String vehicleBrand;

	private String vehicleType;

//	private BrandDto brand;

	private List<VehicleDto> vehicle;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public BrandDto getBrand() {
//		return brand;
//	}
//
//	public void setBrand(BrandDto brand) {
//		this.brand = brand;
//	}

	public List<VehicleDto> getVehicle() {
		return vehicle;
	}

	public void setVehicle(List<VehicleDto> vehicle) {
		this.vehicle = vehicle;
	}
	

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	

}
