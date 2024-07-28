package com.leucine.Assignment.dao;

import com.leucine.Assignment.enums.ClassName;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@Entity
@Table(name = "classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_name", nullable = false)
    private ClassName className;

    @Column(name = "student_id", nullable = false)
    private Long studentId;


}