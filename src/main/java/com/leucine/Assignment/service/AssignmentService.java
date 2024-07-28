package com.leucine.Assignment.service;

import com.leucine.Assignment.dao.Assignments;
import com.leucine.Assignment.dto.AssignmentDTO;
import com.leucine.Assignment.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    public Assignments createAssignment(AssignmentDTO assignmentDTO, Long userId) throws IOException {
        Assignments assignment = new Assignments();
        assignment.setTitle(assignmentDTO.getTitle());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setDueDate(assignmentDTO.getDueDate());
        assignment.setCreatedBy(userId);

        MultipartFile file = assignmentDTO.getData();
        if (file != null && !file.isEmpty()) {
            assignment.setFile(file.getOriginalFilename());

        }

        return assignmentsRepository.save(assignment);
    }
}