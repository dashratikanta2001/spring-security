package com.ovms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(InvalidVehicleTypeException.class)
	public ResponseEntity<?> InvalidVehicleTypeExceptionHandler(InvalidVehicleTypeException ex)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
