package com.leucine.Assignment.dao;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String photo;
    private Long departmentId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
}
