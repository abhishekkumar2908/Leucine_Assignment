package com.leucine.Assignment.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Faculty {
    @Id
    private Long userId;

    private String photo;
    private Long departmentId;
    private String officeHours;

    @OneToMany(mappedBy = "faculty")
    private Set<Course> courses;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
