package com.ovms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovms.dto.VehicleDto;
import com.ovms.response.CustomeResponse;
import com.ovms.service.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/new/register")
	public ResponseEntity<?> addNewVehicle(@RequestBody VehicleDto vehicleDto)
	{
		CustomeResponse<VehicleDto> response=vehicleService.addNewVehicle(vehicleDto);
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
