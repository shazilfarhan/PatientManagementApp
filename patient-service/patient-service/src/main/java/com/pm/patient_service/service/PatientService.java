package com.pm.patient_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;

@Service
public class PatientService {
	
	private PatientRepository patientRepo;

	public PatientService(PatientRepository patientRepo) {
		this.patientRepo = patientRepo;
	}
	
	public List<PatientResponseDTO> getPatients(){
		List<Patient> patients = patientRepo.findAll();
		List<PatientResponseDTO> pDTO = patients.stream().map(patient -> PatientMapper.toDto(patient)).toList();
		return pDTO;
	}
	
	public PatientResponseDTO creatPatient(PatientRequestDTO patientRequestDTO) {
		if(patientRepo.existsByEmail(patientRequestDTO.getEmail())) {
			throw new EmailAlreadyExistsException("A patient with this email already exists" + 
		patientRequestDTO.getEmail());
			
		}
		Patient patient = patientRepo.save(PatientMapper.toModel(patientRequestDTO));
		
		return PatientMapper.toDto(patient);
	}
	
	public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
		
	
		Patient patient = patientRepo.findById(id).orElseThrow(() ->  new PatientNotFoundException("Patient not found with "+ id));
		
		if(patientRepo.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
			throw new EmailAlreadyExistsException("A patient with this email already exists" + 
		patientRequestDTO.getEmail());
			
		}
		patient.setName(patientRequestDTO.getName());
		patient.setAddres(patientRequestDTO.getAddress());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
		patient.setEmail(patientRequestDTO.getEmail());
		
		Patient updatedPatient = patientRepo.save(patient);
		
				

		return PatientMapper.toDto(updatedPatient);
	}
	
	public void deletePatient(UUID id) {
		
		Patient patient = patientRepo.findById(id).orElseThrow(() ->  new PatientNotFoundException("Patient not found with "+ id));
		patientRepo.delete(patient);
		
	}
	
	

}
