package com.ovms.dao;

import java.util.List;

import com.ovms.entity.Vehicle;

public interface VehicleDao {


	Vehicle addNewVehicle(Vehicle vehicle);

	List<Vehicle> findByShowroom(String showroom);
	
	List<Vehicle> findByShowroomAndBrand(String showroom, String brand);
}
