package com.springbootleaning.hostpitalManagement.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder // this make the object creation cleaner
/*
User user = User.builder()
        .name("Ram")
        .email("ram@gmail.com")
        .age(22)
        .build();
 */
@AllArgsConstructor
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne
    @JoinColumn(
            name = "patient_id",
            nullable = false
    )
    @ToString.Exclude
    private Patient patient; // patient is required field, nullable=false
    // this is the owning side

    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;
    // this is owning side

}
