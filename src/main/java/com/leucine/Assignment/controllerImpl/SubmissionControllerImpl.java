package com.leucine.Assignment.controllerImpl;

import com.leucine.Assignment.controller.SubmissionController;
import com.leucine.Assignment.dao.SubmittedAssignment;
import com.leucine.Assignment.dto.SubmissionDTO;
import com.leucine.Assignment.service.SubmissionService;
import com.leucine.Assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubmissionControllerImpl implements SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Object> createSubmission(SubmissionDTO submissionDTO) {
        return ResponseEntity.ok(submissionService.createSubmission(submissionDTO, userService.getUserId()));
    }


    @Override
    public ResponseEntity<List<SubmittedAssignment>> getSubmissionsByTeacherId() {
        return ResponseEntity.ok(submissionService.getSubmissionsByTeacherId(userService.getUserId()));
    }

}