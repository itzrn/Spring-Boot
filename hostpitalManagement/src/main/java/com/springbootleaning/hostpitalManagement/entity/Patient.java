package com.springbootleaning.hostpitalManagement.entity;

import com.springbootleaning.hostpitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(
//        name = "patient_tbl",
        uniqueConstraints = {
//                @UniqueConstraint(name="unique_patient_email", columnNames = {"email"}), // this shows there wouldn't be any row with same email
                @UniqueConstraint(name="unique_patient_name_dob", columnNames = {"name", "dob"}) // this show that there wouldn't be any row with same name and same dob
        },
        indexes = { // time complexity get logn
                // suppose we made the index on dob then doing query based on dob will get faster, bcz already index would have get created in the database
                @Index(name="idx_patient_dob", columnList = "dob")

        } // these are something which allows the rule for data retrieval at the time of query
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
//            name = "patient_name",
            nullable = false
    )
    private String name;

//    @ToString.Exclude
    private LocalDate dob;

    @Column( // if making one column unique
            unique = true
    )
    private String email;
    private String gender;

    @CreationTimestamp // this annotation will fill the field with the current time stamp
    @Column(updatable = false) // this field won't get update in future
    private LocalDateTime createdAt;

    // basically it will use number to corresponding string,
    // A_POSITIVE is 0, A_NEGATIVE is 1 .....
    @Enumerated(EnumType.STRING)
    private BloodGroupType blood_group;


    // CascadeType.MERGE -> used at the time of updating
    // CascadeType.PERSIST -> used whn you are saving something for the first time
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true) // this makes if patient get save then its insurance also get save
//    @JoinColumn(name = "patient_insurance_id") // this is used to put custom name to the join(column with which the two tables get join) column
//    @JoinColumn(nullable = false) // when you want a column not to null
    private Insurance insurance;
    // this makes the connection from patient to insurance
    // means we have patient then we can also get that patient's insurance information too
    // here one insurance_id column will get create
    // this is owning side



    // A patient can have many appointments
    // orphanRemoval = true -> means when there is no appointment for a patient then delete the patient
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER) // cascade bcz if patient is deleted then delete all its appointment
//    @ToString.Exclude
    private List<Appointment> appointments=new ArrayList<>();
    // this is the inverse side
    // in table it won't add the column appointments, instead this helps JPA to remember if you want all appointment of patient
    // so using patient you can write the query to get it's all appointment
    // putting fetch = FetchType.EAGER) -> will create N+1 problem, bcz after getting a patient it will start to run the query for each appointment to get it
    // Solution
    //      1 -> use FetchType.LAZY
}
