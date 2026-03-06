package com.example.space.ingestion.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.space.ingestion.primarymodel.ErrorResponse;

@ControllerAdvice
public class GolbalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex){
		   
		
		
		ErrorResponse er =new ErrorResponse();
		er.setStatus(HttpStatus.NOT_FOUND.value());
		er.setMessage(ex.getMessage());
		er.setTimestamp(System.currentTimeMillis());
		er.setMessage(ex.getMessage());
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
		
		
	}
	

}
