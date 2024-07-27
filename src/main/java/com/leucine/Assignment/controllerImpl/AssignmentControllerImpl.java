package com.leucine.Assignment.controller;

import com.leucine.Assignment.dao.Assignments;
import com.leucine.Assignment.dto.AssignmentDTO;
import com.leucine.Assignment.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/assignments", produces = "application/json")
public class AssignmentControllerImpl implements AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Override
    public Assignments createAssignmentNoFile(AssignmentDTO assignmentDTO) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        return assignmentService.createAssignment(assignmentDTO, userId);

    }

    @Override
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