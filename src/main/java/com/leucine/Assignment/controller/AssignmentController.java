package com.leucine.Assignment.controller;

import com.leucine.Assignment.dto.AssignmentDTO;
import com.leucine.Assignment.dto.SubmittedAssignmentDTO;
import com.leucine.Assignment.enums.ClassName;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AssignmentController {


    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(consumes = {"multipart/form-data"})
    ResponseEntity<Object> createAssignment(@ModelAttribute AssignmentDTO assignmentDTO);

    @GetMapping("/{className}")
    ResponseEntity<Object> getAssignmentsByClass(@PathVariable ClassName className);

    @GetMapping("")
    ResponseEntity<Object> getAssignments();

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping("/{id}")
    ResponseEntity<Object> updateAssignment(@PathVariable Long id, @ModelAttribute AssignmentDTO assignmentDTO);

    @GetMapping("/summary")
    ResponseEntity<Object> getAssignmentSummary(@RequestParam ClassName className);

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteAssignment(@PathVariable Long id);
    }