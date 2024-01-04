package com.ovms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ovms.dto.VehicleDto;

@Entity
@Table(name = "vehicle")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "vehicle_number")
	private String vehicleNumber;

	@Column(name = "engine_number")
	private String engineNumber;

	@Column(name = "chesis_number")
	private String chesisNumber;

	@Column(name = "registration_date")
	private Date regDate;

	@Column(name = "assign_date")
	private Date assignDate;

//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private Brands brand;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "showroom_id", referencedColumnName = "id")
	private Showroom showroom;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
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

//	public Brands getBrand() {
//		return brand;
//	}
//
//	public void setBrand(Brands brand) {
//		this.brand = brand;
//	}

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

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
//	public Vehicle(Integer id, String vehicleNumber, String engineNumber, String chesisNumber, Date regDate,
//			Date assignDate, Brands brand, Showroom showroom, Customer customer) {
//		super();
//		this.id = id;
//		this.vehicleNumber = vehicleNumber;
//		this.engineNumber = engineNumber;
//		this.chesisNumber = chesisNumber;
//		this.regDate = regDate;
//		this.assignDate = assignDate;
//		this.brand = brand;
//		this.showroom = showroom;
//		this.customer = customer;
//	}


}	
