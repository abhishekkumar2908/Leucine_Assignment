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

    public Assignments createAssignment(AssignmentDTO assignmentDTO, String userId) throws IOException {
        Assignments assignment = new Assignments();
        assignment.setTitle(assignmentDTO.getTitle());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setDueDate(assignmentDTO.getDueDate());
        assignment.setCreatedBy(userId);

        MultipartFile file = assignmentDTO.getFileUpload();
        if (file != null && !file.isEmpty()) {
            assignment.setData(file.getBytes());
        }

        return assignmentsRepository.save(assignment);
    }
}