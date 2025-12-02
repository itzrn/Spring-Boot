package com.restcontroller.learning.LearningRestAPIs.service.impl;

import com.restcontroller.learning.LearningRestAPIs.dto.AddStudentRequestDTO;
import com.restcontroller.learning.LearningRestAPIs.dto.StudentDTO;
import com.restcontroller.learning.LearningRestAPIs.entity.Student;
import com.restcontroller.learning.LearningRestAPIs.repository.StudentRepo;
import com.restcontroller.learning.LearningRestAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
// this annotation contains Components internally
// this is written to tell that this particular class contain business logics
@RequiredArgsConstructor // this makes constructor corresponding to the attributes, this works when the attributes are marked final
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;
    @Override
    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepo.findAll();
        List<StudentDTO> studentDTOList = students.stream().map(student->new StudentDTO(student.getId(), student.getName(), student.getEmail())).toList();
        return studentDTOList;
    }

    @Override
    public StudentDTO getStudentById(Long id){
        Student student = studentRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with the id "+id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO createNewStudent(AddStudentRequestDTO studentRequestDTO) {
        Student newStudent = modelMapper.map(studentRequestDTO, Student.class);
        Student student = studentRepo.save(newStudent);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        if(studentRepo.existsById(id)){
            studentRepo.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Student does Not exists by ID : "+id);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO) {
        Student student = studentRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with the id "+id));
        modelMapper.map(addStudentRequestDTO, student);
        student=studentRepo.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO updatePartialStudent(Long id, Map<String, Object> update) {
        Student student = studentRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with the id "+id));
        update.forEach((field, val)->{
            switch (field){
                case "name":
                    student.setName((String) val);
                    break;

                case "email":
                    student.setEmail((String) val);
                    break;
                default:throw new IllegalArgumentException("Field is not Supported");
            }
        });
        Student savedStudent=studentRepo.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }


}
