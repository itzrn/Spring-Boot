package com.springbootleaning.hostpitalManagement;

import com.springbootleaning.hostpitalManagement.entity.Insurance;
import com.springbootleaning.hostpitalManagement.entity.Patient;
import com.springbootleaning.hostpitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceServiceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testAssignInsuranceToPatient(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030, 12, 12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);


        // testing of disassociateInsuranceFromPatient
        var newPatient = insuranceService.disassociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);

    }
}
