package com.springbootleaning.hostpitalManagement.repository;

import com.springbootleaning.hostpitalManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}