package com.ovms.controller;

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

import com.ovms.dto.ShowroomDto;
import com.ovms.response.CustomeResponse;
import com.ovms.service.ShowroomService;

@RestController
@RequestMapping("/api/showroom/")
public class ShowroomController {

	@Autowired
	private ShowroomService showroomService;

	@PostMapping
	public ResponseEntity<?> saveShowroom(@Valid @RequestBody ShowroomDto showroomDto) {
		// TODO: process POST request
		CustomeResponse<?> response = showroomService.save(showroomDto);

		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<?> getAllShowroom() {
		CustomeResponse<?> response = showroomService.findAll();
		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/type/{vehicleType}")
	public ResponseEntity<?> GetByVehicleType(@PathVariable ("vehicleType") String type)
	{
		CustomeResponse<?> response = showroomService.findByVehicleType(type);
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/brand/{vehicleBrand}")
	public ResponseEntity<?> GetByVehicleBrand(@PathVariable ("vehicleBrand") String brand)
	{
		CustomeResponse<?> response = showroomService.findByVehicleBrand(brand);
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/city/{city}")
	public ResponseEntity<?> GetByVehicleCity(@PathVariable ("city") String city)
	{
		CustomeResponse<?> response = showroomService.findByCity(city);
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
