package com.leucine.Assignment.service;

import com.leucine.Assignment.dao.Assignments;
import com.leucine.Assignment.dao.SubmittedAssignment;
import com.leucine.Assignment.dto.SubmissionDTO;
import com.leucine.Assignment.repository.SubmittedAssignmentRepository;
import com.leucine.Assignment.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    @Autowired
    private SubmittedAssignmentRepository submissionsRepository;

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    public SubmissionDTO submitAssignment(SubmissionDTO submissionDto, Long userId) {
        // Fetch the assignment to check the due date
        Assignments assignment = assignmentsRepository.findById(submissionDto.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        // Check if the due date has passed
        if (assignment.getDueDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot submit assignment. The due date has already passed.");
        }

        // Proceed with the submission
        return SubmissionDTO.builder()
                .assignmentId(submissionDto.getAssignmentId())
                .studentId(userId)
                .submissionText(submissionDto.getSubmissionText())
                .submissionDate(submissionDto.getSubmissionDate())
                .data(submissionDto.getData())
                .className(submissionDto.getClassName())
                .build();
    }

    public List<SubmittedAssignment> getSubmissionsByTeacherId(Long userId) {
        List<Long> assignmentIds = assignmentsRepository.findByCreatedBy(userId)
                .stream()
                .map(Assignments::getId)
                .collect(Collectors.toList());

        return submissionsRepository.findAllByAssignmentIdIn(assignmentIds);
    }

    public List<SubmittedAssignment> getSubmittedAssignmentsByAssignmentIds(List<Long> assignmentIds) {
        return submissionsRepository.findByAssignmentIdIn(assignmentIds);
    }
}