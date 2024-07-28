package com.leucine.Assignment.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leucine.Assignment.enums.ClassName;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "assignments")
public class Assignments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @FutureOrPresent(message = "Due date must be in the present or future.")
    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @JsonIgnore
    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "file")
    private String file;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_name" , nullable = false)
    private ClassName className;
}