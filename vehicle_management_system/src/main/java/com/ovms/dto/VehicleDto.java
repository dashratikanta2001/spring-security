package com.ovms.dto;

import java.util.Date;

import com.ovms.entity.Brands;
import com.ovms.entity.Customer;
import com.ovms.entity.Showroom;

public class VehicleDto {

	private Integer id;

	private String vehicleNumber;

	private String engineNumber;

	private String chesisNumber;

	private Date regDate;

	private Date assignDate;

	private Brands brand;

	private Showroom showroom;

	private Customer customer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getChesisNumber() {
		return chesisNumber;
	}

	public void setChesisNumber(String chesisNumber) {
		this.chesisNumber = chesisNumber;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

	public Brands getBrand() {
		return brand;
	}

	public void setBrand(Brands brand) {
		this.brand = brand;
	}

	public Showroom getShowroom() {
		return showroom;
	}

	public void setShowroom(Showroom showroom) {
		this.showroom = showroom;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
