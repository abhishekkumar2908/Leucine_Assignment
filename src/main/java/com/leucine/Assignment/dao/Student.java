package com.leucine.Assignment.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String photo;
    private Long departmentId;
    private int year;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
