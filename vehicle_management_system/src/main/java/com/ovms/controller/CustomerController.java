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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovms.authorize.Authorization;
import com.ovms.dto.CustomerDto;
import com.ovms.enums.SecurityConstants;
import com.ovms.response.CustomeResponse;
import com.ovms.response.ErrorResponse;
import com.ovms.service.CustomerService;
import com.ovms.util.RegexPattern;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Authorization authorization;

	@PostMapping
	public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto, HttpServletRequest request,
			HttpServletResponse servletResponse) {

		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}

		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToAddOrUpdateCustomer(header.substring(7));


		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {

			CustomeResponse<?> response = customerService.save(customerDto);

			if (response.getStatus() == HttpStatus.OK.value()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.badRequest().body(authorizeToAddCustomer);


	}

	@PutMapping("/{email}")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable String email,
			HttpServletRequest request, HttpServletResponse servletResponse) {
		RegexPattern.validEmailPattern(email);
		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}

		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToAddOrUpdateCustomer(header.substring(7));

		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {

			CustomeResponse<?> response = customerService.update(email, customerDto);

			if (response.getStatus() == HttpStatus.OK.value()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.badRequest().body(authorizeToAddCustomer);

	}

	@GetMapping("/{email}")
	public ResponseEntity<?> CustomerByEmail(@PathVariable String email, HttpServletRequest request,
			HttpServletResponse servletResponse) {
		RegexPattern.validEmailPattern(email);
		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}

		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToViewDetails(header.substring(7));

		System.out.println("Authorized = " + authorizeToAddCustomer.getStatus());

		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {
			CustomeResponse<?> response = customerService.findByEmail(email);

			if (response.getStatus() == HttpStatus.OK.value()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
					HttpStatus.BAD_REQUEST);

		}
		return ResponseEntity.badRequest().body(authorizeToAddCustomer);
	}

	@GetMapping("/allvehicle/{email}")
	public ResponseEntity<?> getMethodName(@PathVariable String email, HttpServletRequest request,
			HttpServletResponse servletResponse) {

		RegexPattern.validEmailPattern(email);
		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}

		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToViewDetails(header.substring(7));

		System.out.println("Authorized = " + authorizeToAddCustomer.getStatus());

		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {
			CustomeResponse<?> response = customerService.showAllVehicles(email);

			if (response.getStatus() == HttpStatus.OK.value()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.badRequest().body(authorizeToAddCustomer);
	}

	@PostMapping("/{customerId}/assignrole/{roleId}")
	public ResponseEntity<?> assignRole(@PathVariable Integer customerId, @PathVariable Integer roleId,
			HttpServletRequest request, HttpServletResponse servletResponse) {
		// TODO: process POST request

		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}
		CustomeResponse<?> authorizeToAddRole = authorization.authorizeToAddRole(header.substring(7), customerId,
				roleId);

		if (authorizeToAddRole.getStatus() == HttpStatus.OK.value()) {
			CustomeResponse<?> response = customerService.assignRole(customerId, roleId);

			if (response.getStatus() == HttpStatus.OK.value()) {

				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(authorizeToAddRole, HttpStatus.UNAUTHORIZED);

	}

}
