package com.leucine.Assignment.controller;

import com.leucine.Assignment.dto.AssignmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/api/assignments", produces = "application/json")
public interface AssignmentController {

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(consumes = {"multipart/form-data"})
    ResponseEntity<Object> createAssignment(@ModelAttribute AssignmentDTO assignmentDTO);
}