package com.crud.studentmanagementsystem.studentrepo;

import com.crud.studentmanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Student, Integer> -> two things are passed model class, and the data type of primary key
// JPA is used so that we don't need to make any method
public interface StudentRepo extends JpaRepository<Student, Integer> {
}
