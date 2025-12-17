package com.springbootleaning.hostpitalManagement;

import com.springbootleaning.hostpitalManagement.entity.Appointment;
import com.springbootleaning.hostpitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testCreateNewAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11,1, 14, 0, 0))
                .reason("Cough").build();

        var newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);
        System.out.println(newAppointment);



        // another function of testing
        var updatedAppointment = appointmentService.reassignAppointmentToAnotherDoctor(appointment.getId(), 3L);
        System.out.println(updatedAppointment);
    }

}
