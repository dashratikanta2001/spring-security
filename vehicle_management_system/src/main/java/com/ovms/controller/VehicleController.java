package com.ovms.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ovms.authorize.Authorization;
import com.ovms.dto.VehicleDto;
import com.ovms.enums.SecurityConstants;
import com.ovms.response.CustomeResponse;
import com.ovms.response.ErrorResponse;
import com.ovms.service.VehicleService;
import com.ovms.util.RegexPattern;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private Authorization authorization;

	@PostMapping("/new/register")
	public ResponseEntity<?> addNewVehicle(@Valid @RequestBody VehicleDto vehicleDto, HttpServletRequest request,
			HttpServletResponse servletResponse) {
		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}

		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToAddVehicle(header.substring(7));
		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {
			CustomeResponse<VehicleDto> response = vehicleService.addNewVehicle(vehicleDto);
			if (response.getStatus() == HttpStatus.OK.value()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
					HttpStatus.BAD_REQUEST);

		}

		return ResponseEntity.badRequest().body(authorizeToAddCustomer);
	}

	@GetMapping("/showroom/{id}")
	public ResponseEntity<?> showByShowroom(@PathVariable("id") Integer id,
			@RequestParam(value = "registered", required = false) Boolean registered, HttpServletRequest request,
			HttpServletResponse servletResponse) {
		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}

		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToAddVehicle(header.substring(7));
		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {

			CustomeResponse<List<VehicleDto>> response = vehicleService.findByShowroomId(id, registered);
			if (response.getStatus() == HttpStatus.OK.value()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.badRequest().body(authorizeToAddCustomer);
	}

	@GetMapping("/{vehicleNumber}")
	public ResponseEntity<?> getByVehicleNumber(@PathVariable("vehicleNumber") String vehicleNumber,
			HttpServletRequest request, HttpServletResponse servletResponse) {

		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}

		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToViewDetails(header.substring(7));
		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {

			if (RegexPattern.checkVehicleNumberPattern(vehicleNumber)) {
				CustomeResponse<VehicleDto> response = vehicleService.findByVehicleNumber(vehicleNumber);

				if (response.getStatus() == HttpStatus.OK.value()) {
					return new ResponseEntity<>(response, HttpStatus.OK);
				}

//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);			
				return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
						HttpStatus.BAD_REQUEST);

			}
			return new ResponseEntity<>(
					new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), "Invalid Vehicle number format."),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.badRequest().body(authorizeToAddCustomer);

	}

	@PostMapping("{v_id}/register/user/{u_id}")
	public ResponseEntity<?> RegisterVehicleToUser(@PathVariable("v_id") Integer v_id,
			@PathVariable("u_id") Integer u_id, HttpServletRequest request, HttpServletResponse servletResponse) {
		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}

		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToAddVehicle(header.substring(7));
		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {
			CustomeResponse<VehicleDto> response = vehicleService.registerVehicleToUser(v_id, u_id);

			if (response.getStatus() == HttpStatus.OK.value()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.badRequest().body(authorizeToAddCustomer);
	}

}
