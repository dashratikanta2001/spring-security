package com.ovms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.ovms.authorize.Authorization;
import com.ovms.dto.ShowroomDto;
import com.ovms.enums.RoleType;
import com.ovms.enums.SecurityConstants;
import com.ovms.response.CustomeResponse;
import com.ovms.response.ErrorResponse;
import com.ovms.service.ShowroomService;

@RestController
@RequestMapping("/api/showroom/")
public class ShowroomController {

	@Autowired
	private ShowroomService showroomService;
	
	@Autowired
	private Authorization authorization;

	@PostMapping
	public ResponseEntity<?> saveShowroom(@Valid @RequestBody ShowroomDto showroomDto, HttpServletRequest request,
			HttpServletResponse servletResponse) {
		// TODO: process POST request
		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}
		
		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToAddBrand(header.substring(7));
		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {
			CustomeResponse<?> response = showroomService.save(showroomDto);
			
			if (response.getStatus() == HttpStatus.OK.value()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			
//		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.badRequest().body(authorizeToAddCustomer);

	}

	@GetMapping
	public ResponseEntity<?> getAllShowroom() {
		CustomeResponse<?> response = showroomService.findAll();
		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

//		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
				HttpStatus.BAD_REQUEST);
		
	}

	@GetMapping("/type/{vehicleType}")
	public ResponseEntity<?> GetByVehicleType(@PathVariable("vehicleType") String type) {
		CustomeResponse<?> response = showroomService.findByVehicleType(type);

		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/brand/{vehicleBrand}")
	public ResponseEntity<?> GetByVehicleBrand(@PathVariable("vehicleBrand") String brand) {
		CustomeResponse<?> response = showroomService.findByVehicleBrand(brand);

		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<?> GetByVehicleCity(@PathVariable("city") String city) {
		CustomeResponse<?> response = showroomService.findByCity(city);

		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

}
