package com.springbootleaning.hostpitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(unique = true, length = 100)
    private String email;

    @ManyToMany(mappedBy = "doctors", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Department> departments = new HashSet<>();
}
