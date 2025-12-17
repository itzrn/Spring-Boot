package com.springbootleaning.hostpitalManagement.repository;

import com.springbootleaning.hostpitalManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}