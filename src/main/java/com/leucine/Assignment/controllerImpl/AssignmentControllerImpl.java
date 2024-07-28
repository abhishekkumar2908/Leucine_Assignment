package com.leucine.Assignment.controllerImpl;

import com.leucine.Assignment.controller.AssignmentController;
import com.leucine.Assignment.dao.Assignments;
import com.leucine.Assignment.dto.AssignmentDTO;
import com.leucine.Assignment.enums.ClassName;
import com.leucine.Assignment.service.AssignmentService;
import com.leucine.Assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @GetMapping("/{className}")
    @Override
    public ResponseEntity<Object> getAssignmentsByClass(ClassName className) {
        List<Assignments> assignments = assignmentService.getAssignmentsByClass(className, userService.getUserId());
        if (assignments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(assignments);
    }


        @Override
        public ResponseEntity<Object> getAssignments () {
            List<Assignments> assignments = assignmentService.getAssignmentsCreatedBy(userService.getUserId());
            if (assignments.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(assignments);
        }

        @Override
        public ResponseEntity<Object> updateAssignment (Long id, AssignmentDTO assignmentDTO){
            Optional<Assignments> assignmentsFromDocDb = assignmentService.getAssignmentById(id);
            assignmentsFromDocDb.ifPresent(assignments -> {
                if (!Objects.equals(assignments.getCreatedBy(), userService.getUserId())) {
                    throw new RuntimeException("You are not authorized to update this assignment");
                }
                assignments.setTitle(assignmentDTO.getTitle());
                assignments.setDescription(assignmentDTO.getDescription());
                assignments.setDueDate(LocalDateTime.parse(assignmentDTO.getDueDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // Parse dueDate
                assignments.setClassName(ClassName.valueOf(assignmentDTO.getClassName().name()));
                assignments.setFile(assignmentDTO.getFile().getOriginalFilename());
                assignments.setCreatedBy(userService.getUserId());
                try {
                    assignmentService.createAssignment(assignmentDTO, userService.getUserId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return ResponseEntity.ok(assignmentsFromDocDb);
        }

    }
