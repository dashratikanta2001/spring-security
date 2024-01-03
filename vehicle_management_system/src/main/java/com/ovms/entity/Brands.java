package com.ovms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ovms.enums.VehicleType;

@Entity
@Table(name = "brand")
public class Brands {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "vehicle_type")
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;

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

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	
	
	public Brands(Integer id, String name, VehicleType vehicleType) {
		super();
		this.id = id;
		this.name = name;
		this.vehicleType = vehicleType;
	}

	public Brands() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Brands [id=" + id + ", name=" + name + ", vehicleType=" + vehicleType + "]";
	}

	
}
