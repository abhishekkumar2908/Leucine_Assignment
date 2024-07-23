package com.leucine.Assignment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDTO {
    // User fields
    private String username;
    private String email;
    private String password;
    private String role;
    private String name;
    private String phone;

    // Student fields
    private Long departmentId;
    private String photo;

    @NotBlank(message = "Study year is mandatory")
    private String year;

    // Getters and Setters
}