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
import com.ovms.dto.BrandDto;
import com.ovms.enums.SecurityConstants;
import com.ovms.response.CustomeResponse;
import com.ovms.response.ErrorResponse;
import com.ovms.service.BrandService;


@RestController
@RequestMapping("/api/brand")
public class BrandsController {

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private Authorization authorization;
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody BrandDto brandDto, HttpServletRequest request,
			HttpServletResponse servletResponse) {
		
		String header = request.getHeader(SecurityConstants.HEADER.getHeader());
		if (header == null) {
			return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized."),
					HttpStatus.UNAUTHORIZED);
		}
		
		CustomeResponse<?> authorizeToAddCustomer = authorization.authorizeToAddBrand(header.substring(7));
		if (authorizeToAddCustomer.getStatus() == HttpStatus.OK.value()) {
			CustomeResponse<?> response = brandService.save(brandDto);
			
			if (response.getStatus() == HttpStatus.OK.value()) {
				return new ResponseEntity<>(response, HttpStatus.OK);			
			}
			
			return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()), HttpStatus.BAD_REQUEST);
			
		}
		
		return ResponseEntity.badRequest().body(authorizeToAddCustomer);
	}
	
	
//	@GetMapping("/{name}")
//	public ResponseEntity<?> findByName(@PathVariable String name) {
//		
//		brandService.save();
//		
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
	
	@GetMapping
	public ResponseEntity<?> findAll( HttpServletRequest request,
			HttpServletResponse servletResponse) {
		
		CustomeResponse<?> response = brandService.findAll();
		if (response.getStatus() == HttpStatus.OK.value()) {
			return new ResponseEntity<>(response, HttpStatus.OK);			
		}
		
		return new ResponseEntity<>(new ErrorResponse<>(response.getStatus(), response.getMessage()), HttpStatus.BAD_REQUEST);
		
	}
	
	
}
