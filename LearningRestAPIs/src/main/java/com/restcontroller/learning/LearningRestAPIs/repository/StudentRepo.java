package com.restcontroller.learning.LearningRestAPIs.repository;

import com.restcontroller.learning.LearningRestAPIs.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}

// these are the class where you write the sql queries which perform actions on database
