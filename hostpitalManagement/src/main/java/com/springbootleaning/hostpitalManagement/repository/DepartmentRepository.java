package com.springbootleaning.hostpitalManagement.repository;

import com.springbootleaning.hostpitalManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}