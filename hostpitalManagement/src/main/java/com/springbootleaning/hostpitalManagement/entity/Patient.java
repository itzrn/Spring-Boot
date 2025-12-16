package com.springbootleaning.hostpitalManagement.entity;

import com.springbootleaning.hostpitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Table(
//        name = "patient_tbl",
        uniqueConstraints = {
                @UniqueConstraint(name="unique_patient_email", columnNames = {"email"}), // this shows there wouldn't be any row with same email
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
}
