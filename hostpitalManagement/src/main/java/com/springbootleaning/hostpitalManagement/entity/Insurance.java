package com.springbootleaning.hostpitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String policyNumber;

    @Column(nullable = false, length = 100)
    private String provider;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp // this automatically comes from hibernate, get filled automatically when any row get add
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "insurance")
    private Patient patient; // this column will not get create in the insurance table instead it tells to hibernate to have bidirectional mapping
    // means if we have insurance then we can also get the information of patient
    // this is inverse side
    // both side having owning side is not possible

}
