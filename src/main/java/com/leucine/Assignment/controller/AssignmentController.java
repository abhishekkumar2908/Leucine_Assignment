package com.leucine.Assignment.controller;

import com.leucine.Assignment.dto.AssignmentDTO;
import com.leucine.Assignment.enums.ClassName;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface AssignmentController {


    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(consumes = {"multipart/form-data"})
    ResponseEntity<Object> createAssignment(@ModelAttribute AssignmentDTO assignmentDTO);

    @GetMapping("/assignments/{className}")
    ResponseEntity<Object> getAssignmentsByClass(@PathVariable ClassName className);

}