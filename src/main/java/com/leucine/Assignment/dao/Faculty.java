package com.leucine.Assignment.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    private String photo;
    private Long departmentId;
    private String officeHours;

    @OneToMany(mappedBy = "faculty")
    private Set<Course> courses;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
