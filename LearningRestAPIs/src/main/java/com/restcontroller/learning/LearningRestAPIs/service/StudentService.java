package com.restcontroller.learning.LearningRestAPIs.service;

import com.restcontroller.learning.LearningRestAPIs.dto.AddStudentRequestDTO;
import com.restcontroller.learning.LearningRestAPIs.dto.StudentDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO createNewStudent(AddStudentRequestDTO studentRequestDTO);
    void deleteById(Long id);
    StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO);
    StudentDTO  updatePartialStudent(Long id, Map<String, Object> update);
}
