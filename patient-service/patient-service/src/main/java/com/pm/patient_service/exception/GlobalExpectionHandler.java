package com.pm.patient_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pm.patient_service.service.PatientNotFoundException;

@ControllerAdvice
public class GlobalExpectionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach
		(error -> errors.put(error.getField(),error.getDefaultMessage()));
		
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Map<String,String>> handleEmailAlreadyExistException(EmailAlreadyExistsException ex){
		
		Map<String, String> errors = new HashMap<>();
		errors.put("message","Email Already Exist");
		
		
		return ResponseEntity.badRequest().body(errors);
	
	}
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException ex){
		
		Map<String, String> errors = new HashMap<>();
		errors.put("message","Patient does not exist");
		
		
		return ResponseEntity.badRequest().body(errors);
	
	}
}
