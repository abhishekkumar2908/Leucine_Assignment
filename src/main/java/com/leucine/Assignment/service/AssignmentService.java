package com.leucine.Assignment.service;

import com.leucine.Assignment.dao.Assignments;
import com.leucine.Assignment.dto.AssignmentDTO;
import com.leucine.Assignment.enums.ClassName;
import com.leucine.Assignment.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
        assignment.setClassName(ClassName.valueOf(assignmentDTO.getClassName().name()));

        MultipartFile file = assignmentDTO.getData();
        if (file != null && !file.isEmpty()) {
            assignment.setFile(file.getOriginalFilename());

        }

        return assignmentsRepository.save(assignment);
    }

    public Object getAssignments(ClassName className, Long userId) {
        return assignmentsRepository.findByClassName(className, userId);
    }

    public List<Assignments> getAssignmentsCreatedBy(Long userId) {
        return assignmentsRepository.findByCreatedBy(userId);
    }
}