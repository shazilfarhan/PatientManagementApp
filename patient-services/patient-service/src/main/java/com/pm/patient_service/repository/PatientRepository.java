package com.pm.patient_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.patient_service.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
 
	public boolean existsByEmail(String email);
	public boolean existsByEmailAndIdNot(String Email, UUID id);
	
}
