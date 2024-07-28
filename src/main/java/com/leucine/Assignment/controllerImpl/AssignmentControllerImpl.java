package com.leucine.Assignment.controllerImpl;

import com.leucine.Assignment.controller.AssignmentController;
import com.leucine.Assignment.dto.AssignmentDTO;
import com.leucine.Assignment.enums.ClassName;
import com.leucine.Assignment.service.AssignmentService;
import com.leucine.Assignment.service.UserService;
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

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Object> createAssignment(@ModelAttribute AssignmentDTO assignmentDTO) {
        try {
            return ResponseEntity.ok(assignmentService.createAssignment(assignmentDTO, userService.getUserId()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/assignments/{className}")
    @Override
    public ResponseEntity<Object> getAssignmentsByClass(ClassName className) {
        return ResponseEntity.ok(assignmentService.getAssignments(className, userService.getUserId()));
    }


}