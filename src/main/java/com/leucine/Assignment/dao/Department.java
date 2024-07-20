package com.leucine.Assignment.dao;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;

    // Getters and Setters
}
