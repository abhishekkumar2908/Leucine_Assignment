package com.leucine.Assignment.dao;


import jakarta.persistence.*;


@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}