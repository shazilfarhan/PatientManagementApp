package com.pm.patient_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patient_service.dto.CreatePatientValidationGroup;
import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patients", description = "Api for patients")
public class PatientController {
	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	
	  @GetMapping() 
	  @Operation(summary = "get patients")
	  public ResponseEntity<List<PatientResponseDTO>> getPatients(){
		  List<PatientResponseDTO> patients= patientService.getPatients(); return
		  ResponseEntity.ok().body(patients); 
	  }
	 

	
	  @PostMapping
	  @Operation(summary = "create patients")
	  public ResponseEntity<PatientResponseDTO>
		  createPatient(@RequestBody @Validated({Default.class, CreatePatientValidationGroup.class}) PatientRequestDTO patientRequestDTO){
		  PatientResponseDTO patientResponseDTO =patientService.creatPatient(patientRequestDTO); 
		  return ResponseEntity.ok().body(patientResponseDTO);
	  
	  }
	  
	  @PutMapping("/{id}")
	  @Operation(summary = "update patients")
	  public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @RequestBody @Validated({Default.class}) PatientRequestDTO patientRequestDTO ){
		  PatientResponseDTO patientResponseDTO =patientService.updatePatient(id, patientRequestDTO); 
		  return ResponseEntity.ok().body(patientResponseDTO);
	  }
	  
	  @DeleteMapping("/{id}")
	  @Operation(summary = "delete patients")
	  public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
		  patientService.deletePatient(id);
		  return ResponseEntity.noContent().build();
	  }
	    
	 

}
