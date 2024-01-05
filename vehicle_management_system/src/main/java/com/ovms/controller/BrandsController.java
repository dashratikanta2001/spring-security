package com.ovms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovms.dto.BrandDto;
import com.ovms.response.CustomeResponse;
import com.ovms.service.BrandService;


@RestController
@RequestMapping("/api/brand")
public class BrandsController {

	@Autowired
	private BrandService brandService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody BrandDto brandDto) {
		
		 CustomeResponse<?> response = brandService.save(brandDto);
		 
		 if (response.getStatus() == HttpStatus.OK.value()) {
			 return new ResponseEntity<>(response, HttpStatus.OK);			
		}
		 
		 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
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
	public ResponseEntity<?> findAll() {
		
		CustomeResponse<?> response = brandService.findAll();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}
