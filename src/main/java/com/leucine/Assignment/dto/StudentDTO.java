package com.leucine.Assignment.dto;

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
    private String year;

    // Getters and Setters
}