package com.springbootleaning.hostpitalManagement.service;

import com.springbootleaning.hostpitalManagement.entity.Patient;
import com.springbootleaning.hostpitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    @Transactional // putting this to any method will make the cache of query which won't let happen the same query to perform on the database again and again
    public Patient getPatientById(Long id){
        Patient p1 = patientRepository.findById(id).orElseThrow();
//        Patient p2 = patientRepository.findById(id).orElseThrow();
        // this p1 and p2 are the same object in the memory cache, which is automatically getting handel by the Application context
        p1.setName("Aryan"); // changing the name

        // patientRepository.save(p1);
        // you would bw thinking now I have to again call the .save method to make it update
        // but no this object is in the transactional context mean p1 already have the state transactional
        // so when it get commit then there is a dirty check that isn't there any change in the values
        // if changed the updated one will get commit
        return p1;
    }
}
