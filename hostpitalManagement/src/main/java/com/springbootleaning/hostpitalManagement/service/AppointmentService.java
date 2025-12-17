package com.springbootleaning.hostpitalManagement.service;

import com.springbootleaning.hostpitalManagement.entity.Appointment;
import com.springbootleaning.hostpitalManagement.entity.Doctor;
import com.springbootleaning.hostpitalManagement.entity.Patient;
import com.springbootleaning.hostpitalManagement.repository.AppointmentRepository;
import com.springbootleaning.hostpitalManagement.repository.DoctorRepository;
import com.springbootleaning.hostpitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have ");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);// this is just to maintain bidirectional consistency

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reassignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); // this will automatically call the update bcz it is dirty now
        return appointment;
    }
}
