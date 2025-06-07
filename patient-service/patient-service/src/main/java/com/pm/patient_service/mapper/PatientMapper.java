package com.pm.patient_service.mapper;

import java.time.LocalDate;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;

public class PatientMapper {
	
	public static PatientResponseDTO toDto(Patient patient) {
		PatientResponseDTO pDTO = new PatientResponseDTO();
		pDTO.setId(patient.getId().toString());
		pDTO.setEmail(patient.getEmail());
		pDTO.setName(patient.getName());
		pDTO.setAddress(patient.getAddres());
		pDTO.setDateOfBirth(patient.getDateOfBirth().toString());
		
		return pDTO;
	}
	
	public static Patient toModel(PatientRequestDTO patientDTO) {
		
		Patient patient = new Patient();
		patient.setName(patientDTO.getName());
		patient.setDateOfBirth(LocalDate.parse(patientDTO.getDateOfBirth()));
		patient.setEmail(patientDTO.getEmail());
		patient.setAddres(patientDTO.getAddress());
		patient.setRegisteredDate(LocalDate.parse(patientDTO.getRegisteredDate()));
		
		return patient;
	}

}
