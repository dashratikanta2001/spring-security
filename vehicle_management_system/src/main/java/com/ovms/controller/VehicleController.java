package com.ovms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<?> addNewVehicle(@Valid @RequestBody VehicleDto vehicleDto)
	{
		CustomeResponse<VehicleDto> response=vehicleService.addNewVehicle(vehicleDto );
		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);			
		}
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);			
	}
	
	
	@GetMapping("/showroom/{id}")
	public ResponseEntity<?> showByShowroom(@PathVariable("id") Integer id) {
		
		CustomeResponse<List<VehicleDto>> response = vehicleService.findByShowroomId(id);
		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);			
		}
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
}
