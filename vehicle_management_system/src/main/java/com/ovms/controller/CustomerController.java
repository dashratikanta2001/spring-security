package com.ovms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovms.dto.CustomerDto;
import com.ovms.response.CustomeResponse;
import com.ovms.response.ErrorResponse;
import com.ovms.service.CustomerService;
import com.ovms.util.RegexPattern;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
		
		CustomeResponse<?> response = customerService.save(customerDto);

		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
				HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/{email}")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable String email) {
		RegexPattern.validEmailPattern(email);
		CustomeResponse<?> response = customerService.update(email, customerDto);

		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
				HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/{email}")
	public ResponseEntity<?> CustomerByEmail(@PathVariable String email) {
		RegexPattern.validEmailPattern(email);
		CustomeResponse<?> response = customerService.findByEmail(email);

		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
				HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/allvehicle/{email}")
	public ResponseEntity<?> getMethodName(@PathVariable String email) {

		RegexPattern.validEmailPattern(email);

		CustomeResponse<?> response = customerService.showAllVehicles(email);

		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

}
