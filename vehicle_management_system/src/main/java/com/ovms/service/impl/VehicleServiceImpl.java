package com.ovms.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovms.dao.VehicleDao;
import com.ovms.dto.VehicleDto;
import com.ovms.response.CustomeResponse;
import com.ovms.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;
	
	@Override
	public CustomeResponse<VehicleDto> addNewVehicle(VehicleDto vehicleDto) {
		
	return null;
	}

	
	
	
	private String GenerateVehicleNumber(String brand)
	{
		final String firstName = "DEMO";
		int year = new Date().getYear();
		
		return null;
	}
}
