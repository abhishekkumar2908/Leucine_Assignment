package com.leucine.Assignment.controller;

import com.leucine.Assignment.dao.SubmittedAssignment;
import com.leucine.Assignment.dto.SubmissionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping(path = "/api/submissions", produces = "application/json")
public interface SubmissionController {

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping(consumes = {"multipart/form-data"})
    ResponseEntity<Object> submitAssignment(@ModelAttribute SubmissionDTO submissionDTO) throws IOException;


    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/teacher")
    ResponseEntity<List<SubmittedAssignment>> getSubmissionsByTeacherId();

}