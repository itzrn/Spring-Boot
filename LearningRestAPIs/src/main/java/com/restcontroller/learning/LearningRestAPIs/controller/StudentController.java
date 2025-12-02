package com.restcontroller.learning.LearningRestAPIs.controller;

import com.restcontroller.learning.LearningRestAPIs.dto.AddStudentRequestDTO;
import com.restcontroller.learning.LearningRestAPIs.dto.StudentDTO;
import com.restcontroller.learning.LearningRestAPIs.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
// it is made of Two types -> Controller(Use to handel the mapping request to api) and ResponseBody(this tell the response will get return in the form of json instead of HTML)
@AllArgsConstructor
@RequestMapping("students")
public class StudentController {
    private final StudentServiceImpl studentService;


    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    // we can give here more than one variable by putting '/'
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id){ // or @PathVariable("id") Long studentId
//        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
        // or we have short hand for .ok
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    // @Valid annotation just check the validation of AddStudentRequestDTO, if get fines then it enter the function else it throw erroe
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody @Valid AddStudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(studentRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAStudent(@PathVariable Long id){
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // put mapping is used when you want to change the whole row
    // patch mapping is used when you want to change a little things in a row

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id,
                                                    @RequestBody AddStudentRequestDTO addStudentRequestDTO){
        return ResponseEntity.ok(studentService.updateStudent(id, addStudentRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> updatePartialStudent(@PathVariable Long id,
                                                           @RequestBody Map<String, Object> update){
        return ResponseEntity.ok(studentService.updatePartialStudent(id, update));
    }

}

// in case of GetMapping successfully get done then it is ok status 200
// in case PostMapping successfully done then status is 201

