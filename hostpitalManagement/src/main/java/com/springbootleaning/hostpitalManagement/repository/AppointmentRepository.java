package com.springbootleaning.hostpitalManagement.repository;

import com.springbootleaning.hostpitalManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}