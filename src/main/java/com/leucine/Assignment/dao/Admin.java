package com.leucine.Assignment.dao;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Admin {
    @Id
    private Long userId;

    private String photo;
    private Long departmentId;

    // Getters and Setters
}
