package com.springbootleaning.hostpitalManagement.repository;

import com.springbootleaning.hostpitalManagement.entity.BloodGroupCountResponseEntity;
import com.springbootleaning.hostpitalManagement.entity.Patient;
import com.springbootleaning.hostpitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name); // this types are JPA query method which directly do query on using the attribute of Entity
    // you have to write findByCamelCasing(Datatype(as used in Entity) variableName);
    // this interface get implemented automatically by spring data jpa(Hibernate)

    Patient findByDob(LocalDate date);

    Patient findByDobOrEmail(LocalDate dob, String email);
    // now this will get automatically implemented by hibernate, any of this matches will return that patient
    // hibernate able to do these al things just bcz based on some rules, based on that rules these methods names are written

    // according to return type hibernate can also update the query
    Optional<Patient> findByEmail(String email);

    @Query(
            "SELECT p FROM Patient p WHERE p.blood_group = ?1"
    )
    List<Patient> findByBlood(@Param("blood_group") BloodGroupType blood);

    @Query(
            "select p from Patient p where p.dob > :birthDate"
    )
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);


    @Query(
            "select p.blood_group, count(p) from Patient p group by p.blood_group"
    )
    List<Object[]> countEachBloodGroupType();


    @Query(
            "select new com.springbootleaning.hostpitalManagement.entity.BloodGroupCountResponseEntity(p.blood_group, count(p)) from Patient p group by p.blood_group"
    ) // now this function will return BloodGroupCountResponseEntity object
    // this can not be done in native query, from there you can not bring projection bcz it gives the data of all the columns, so u need to use the entity
    // projection is only possible nativeQuery=false, bcz when it is false then hibernate and JPQL is responsible for converting to projection
    List<BloodGroupCountResponseEntity> countEachBloodGroupType1();








    // Native Query to write pure SQL query
    @Query(
            value = "SELECT * FROM Patient",
            nativeQuery = true // keeping this means hibernate have not to generate query, directly this query will go for execution
            // keeping true will directly get passed in the jdbc for the query
    )
    List<Patient> findAllPatient();



    // update query
    // having return type int bcz after execution of query it will return how many row got affected
    @Transactional // this is also required in update query
    @Modifying // this is need to be present when we use update query, which tells to JPQL that this query is supposed to update the table
    @Query(
            "UPDATE Patient p SET p.name=:_name WHERE p.id=:_id"
    )
    int updateNameWithId(@Param("_name") String name, @Param("_id") Long id);





    // pagination
    // suppose you db have millions of data which is need to be shown on front end, instead of showing it all in once we show
    // the data first 20 then other 20 then other 20 .... by this make the load on the db and server low which is possible using
    // pagination -> means decreases the bandwidth when we have to give the data to user and internet uses is less
    @Query(
            value = "SELECT * FROM Patient",
            nativeQuery = true
    ) // wherever you are returning as List there we can use pageable
    Page<Patient> findAllPatient1(Pageable pageable);
    // this return type give all the info about the page





}

// JPQL -> https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html


// in db the last updated row comes to the end of the table