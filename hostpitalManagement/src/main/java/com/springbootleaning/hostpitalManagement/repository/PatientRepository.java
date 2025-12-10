package com.springbootleaning.hostpitalManagement.repository;

import com.springbootleaning.hostpitalManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // this interface get implemented automatically by spring data jpa
}
