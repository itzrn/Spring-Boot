package com.springbootleaning.hostpitalManagement.service;

import com.springbootleaning.hostpitalManagement.entity.Insurance;
import com.springbootleaning.hostpitalManagement.entity.Patient;
import com.springbootleaning.hostpitalManagement.repository.InsuranceRepository;
import com.springbootleaning.hostpitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // u need to make final to the attributes also
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional // using this will either perform all the task or will get roll back
    // if something get dirty then @Transactional use to automatically perform the save to the database
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId).
                orElseThrow(() -> new EntityNotFoundException("Patient Not Found with ID: "+patientId));

        patient.setInsurance(insurance); // the insurance field of patient got updated -> this patient gets dirty
        insurance.setPatient(patient); // this is to maintain bidirectional consistency
        return patient;

        // putting @Transactional this whole block(function) will call as Transactional context

    } // here transactional will get into the commit phase


    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).
                orElseThrow(()->new EntityNotFoundException("Patient Not Found with ID: "+patientId));

        patient.setInsurance(null);
        return patient;
    }
}
