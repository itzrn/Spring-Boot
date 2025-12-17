package com.springbootleaning.hostpitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @OneToOne
    private Doctor headDoctor;
    // this is the owning side

    @ManyToMany
    @JoinTable(
            name = "my_dpt_doctors",
            joinColumns = @JoinColumn(name = "dpt_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctors=new HashSet<>();
    // we will get unique doctors so keeping in hashset, but we need to initialize the set, when hibernate returns the
    // list of doctor then it wont find the set null
    // here join table will get create automatically by hibernate
    // this is the owning side

}
