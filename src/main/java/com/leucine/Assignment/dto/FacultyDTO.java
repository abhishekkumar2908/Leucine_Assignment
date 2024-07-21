package com.leucine.Assignment.dto;

import lombok.Data;

@Data
public class FacultyDTO {
    // User fields
    private String username;
    private String email;
    private String password;
    private String role;
    private String name;
    private String phone;

    // Faculty fields
    private Long departmentId;
    private String photo;
    private String officeHours;

    // Getters and Setters
}