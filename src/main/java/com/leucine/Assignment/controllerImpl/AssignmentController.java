package com.leucine.Assignment.controller;

import com.leucine.Assignment.dto.AssignmentDTO;
import com.leucine.Assignment.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController implements Assignment {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Object> createAssignment(@ModelAttribute AssignmentDTO assignmentDTO) {
        // Get the current authenticated user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        try {
            return ResponseEntity.ok(assignmentService.createAssignment(assignmentDTO, userId));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}