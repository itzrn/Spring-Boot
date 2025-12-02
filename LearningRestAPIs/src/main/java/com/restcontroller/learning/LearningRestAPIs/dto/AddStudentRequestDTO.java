package com.restcontroller.learning.LearningRestAPIs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestDTO {
    @Size(min = 3, max = 30, message = "Name should be of length 3 to 30")
    private String name;

    @Email // -> checks the validation on email
    @NotBlank(message = "email is required") // -> this is a required field
    private String email;
}
