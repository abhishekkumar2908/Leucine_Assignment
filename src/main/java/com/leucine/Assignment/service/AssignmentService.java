package com.leucine.Assignment.service;

import com.leucine.Assignment.dao.Assignments;
import com.leucine.Assignment.dto.AssignmentDTO;
import com.leucine.Assignment.enums.ClassName;
import com.leucine.Assignment.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    public Assignments createAssignment(AssignmentDTO assignmentDTO, Long userId) throws IOException {
        Assignments assignment = new Assignments();
        assignment.setTitle(assignmentDTO.getTitle());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setDueDate(LocalDateTime.parse(assignmentDTO.getDueDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // Parse dueDate
        assignment.setCreatedBy(userId);
        assignment.setClassName(ClassName.valueOf(assignmentDTO.getClassName().name()));

        MultipartFile file = assignmentDTO.getFile();
        if (file != null && !file.isEmpty()) {
            assignment.setFile(file.getOriginalFilename());

        }

        return assignmentsRepository.save(assignment);
    }


    public List<Assignments> getAssignmentsCreatedBy(Long userId) {
        return assignmentsRepository.findByCreatedBy(userId);
    }

    public Optional<Assignments> getAssignmentById(Long id) {

        return assignmentsRepository.findById(id);
    }

    public List<Assignments> getAssignmentsByClass(ClassName className, Long createdBy) {
        return assignmentsRepository.findByClassNameAndCreatedBy(className, createdBy);
    }

    public List<Assignments> getAssignmentsByTeacherAndClass(Long teacherId, ClassName className) {
        return assignmentsRepository.findByCreatedBy(teacherId).stream()
                .filter(assignment -> assignment.getClassName() == className)
                .collect(Collectors.toList());
    }

    public void deleteAssignment(Long id) {
        assignmentsRepository.deleteById(id);
    }
}