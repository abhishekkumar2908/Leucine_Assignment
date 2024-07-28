package com.leucine.Assignment.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "submissions")
public class SubmittedAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "assignment_id", nullable = false)
    private Long assignmentId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "submission_text")
    private String submissionText;

    @Column(name = "attachment_url")
    private String attachmentUrl;

    @Column(name = "submission_date", nullable = false)
    private LocalDateTime submissionDate;
}