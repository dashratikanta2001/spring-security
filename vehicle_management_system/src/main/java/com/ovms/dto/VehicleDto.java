package com.ovms.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VehicleDto {

	private Integer id;

	private String vehicleNumber;

	@Size(min = 11, max = 17, message = "Engine number range from 11 to 17 digits")
	private String engineNumber;

	@Size(min = 17, max = 17, message = "Chesis number must be 17 digits")
	private String chesisNumber;

	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date regDate;

	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date assignDate;

//	private Brands brand;

	//private Integer showroomId;
	
	private ShowroomDto showroom;

	private CustomerDto customer;

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

//	public Brands getBrand() {
//		return brand;
//	}
//
//	public void setBrand(Brands brand) {
//		this.brand = brand;
//	}

	public ShowroomDto getShowroom() {
		return showroom;
	}

	public void setShowroom(ShowroomDto showroom) {
		this.showroom = showroom;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

//	public Integer getShowroomId() {
//		return showroomId;
//	}
//
//	public void setShowroomId(Integer showroomId) {
//		this.showroomId = showroomId;
//	}


}
