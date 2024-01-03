package com.ovms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ovms.enums.VehicleType;

@Entity
@Table(name = "showroom")
public class Showroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_no")
	private String phoneNo;

	@Column(name = "email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id", referencedColumnName = "id")
	private Brands brand;
	
	@OneToMany(mappedBy = "id")
	private List<Vehicle> vehicle;
	
//	@Column(name = "vehicle_type")
//	@Enumerated(EnumType.STRING)
//	private VehicleType vehicleType;

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

	public Brands getBrand() {
		return brand;
	}

	public void setBrand(Brands brand) {
		this.brand = brand;
	}

	public List<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	
	

//	public VehicleType getVehicleType() {
//		return vehicleType;
//	}
//
//	public void setVehicleType(VehicleType vehicleType) {
//		this.vehicleType = vehicleType;
//	}

	

	public Showroom(int id, String name, String address, String phoneNo, String email, Brands brand,
			List<Vehicle> vehicle, VehicleType vehicleType) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.brand = brand;
		this.vehicle = vehicle;
//		this.vehicleType = vehicleType;
	}

	public Showroom() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
