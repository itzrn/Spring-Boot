package com.springbootleaning.hostpitalManagement;

import com.springbootleaning.hostpitalManagement.entity.BloodGroupCountResponseEntity;
import com.springbootleaning.hostpitalManagement.entity.Patient;
import com.springbootleaning.hostpitalManagement.entity.type.BloodGroupType;
import com.springbootleaning.hostpitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PatientTest {
    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatientRepository() {

        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);

        Patient patient = patientRepository.findByName("Diya Patel");
        System.out.println(patient);


        Optional<Patient> p = patientRepository.findByEmail("diya.patel@example.com");
        System.out.println(p+" -> Optional Vala hai ye");


        List<Patient> l = patientRepository.findByBlood(BloodGroupType.A_POSITIVE);
        for(Patient p1:l){
            System.out.println(p1 + " -> ab ye hai khud ki query se ===> findByBlood");
        }


        l = patientRepository.findByBornAfterDate(LocalDate.of(1988, 3, 14));
        for(Patient p1:l){
            System.out.println(p1 + " -> ab ye hai khud ki query se ===> findByBornAfterDate");
        }


        List<Object[]> list = patientRepository.countEachBloodGroupType();
        for(Object[] ob:list){
            System.out.println(ob[0]+" "+ob[1]+ " ==> Group By Clause");
        }



        l = patientRepository.findAllPatient();
        for(Patient p1:l){
            System.out.println(p1 + " -> ab ye hai khud ki query se ===> findAllPatient");
        }



        int rowsUpdated = patientRepository.updateNameWithId("Arav Sharma", 17L);
        System.out.println("Rows Updated --> "+rowsUpdated+" ===> updateNameWithId");



        // this is called Projection
        List<BloodGroupCountResponseEntity> list1 = patientRepository.countEachBloodGroupType1();
        for(BloodGroupCountResponseEntity ob:list1){
            System.out.println(ob.getBloodGroupType()+" "+ob.getCount()+ " ==> Group By Clause ----> countEachBloodGroupType1()");
        }




        Page<Patient> listp = patientRepository.findAllPatient1(PageRequest.of(0, 2)); // -> data will come in two set only for the page 0
        for(Patient p1:listp){
            System.out.println(p1 + " -> ab ye hai khud ki query se ===> findAllPatient1");
        }

    }
}
