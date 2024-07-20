package com.leucine.Assignment.dao;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Student {
    @Id
    private Long userId;

    private String photo;
    private Long departmentId;
    private int year;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;}
