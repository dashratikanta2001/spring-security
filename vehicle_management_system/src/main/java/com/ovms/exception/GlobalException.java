package com.ovms.exception;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ovms.response.ErrorResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(InvalidVehicleTypeException.class)
	public ResponseEntity<?> InvalidVehicleTypeExceptionHandler(InvalidVehicleTypeException ex)
	{
		return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> UsernameNotFoundException(UsernameNotFoundException ex)
	{
		return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> resp = new HashMap<String, String>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);

		});
		
		Map<String, Object> erResp = new LinkedHashMap<String, Object>();
		
		erResp.put("status", HttpStatus.BAD_REQUEST.value());
		erResp.put("message", resp);

		return new ResponseEntity<>(erResp, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		Map<String, String> resp = new HashMap<String, String>();
		
		return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CustomUnauthorizeException.class)
	public ResponseEntity<?> handleUnauthorizeException(CustomUnauthorizeException ex) {
		
		return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex)
	{
		return new ResponseEntity<>(new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
	}	
}
