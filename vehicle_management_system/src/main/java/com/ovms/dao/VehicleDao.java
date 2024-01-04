package com.ovms.dao;

import java.util.List;

import com.ovms.entity.Showroom;
import com.ovms.entity.Vehicle;

public interface VehicleDao {


	Vehicle addNewVehicle(Vehicle vehicle);
	
	Vehicle RegisterVehicle(Vehicle vehicle);
	
	Vehicle findByRegistrationNo(String registrationNo);
	
	Vehicle findByEngineNumber(String engineNumber);
	
	Vehicle findByChesisNumber(String chesisNumber);
	
	Vehicle findByEngineAndChessis(String engineNumber, String chesisNumber);
	
	Integer countLastVehicleNumber(Showroom showroom);

	List<Vehicle> findByShowroom(Showroom showroom);
	
	List<Vehicle> findByShowroomAndBrand(Integer showroom, Integer brand);
}
