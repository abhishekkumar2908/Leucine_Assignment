package com.leucine.Assignment.service;
import com.leucine.Assignment.dto.SubmittedAssignmentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import com.leucine.Assignment.dao.Assignments;
import com.leucine.Assignment.dao.SubmittedAssignment;
import com.leucine.Assignment.dto.SubmissionDTO;
import com.leucine.Assignment.enums.ClassName;
import com.leucine.Assignment.repository.SubmittedAssignmentRepository;
import com.leucine.Assignment.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.UUID;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    @Autowired
    private SubmittedAssignmentRepository submissionsRepository;

    @Autowired
    private AssignmentsRepository assignmentsRepository;


    public SubmissionDTO createSubmission(SubmissionDTO submissionDto, Long userId) {
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




//    public List<SubmittedAssignmentDTO> getSubmittedAssignments(String assignmentTitle, ClassName className, String studentName, UUID createdBy) {
//        Specification<SubmittedAssignment> spec = (root, query, cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            predicates.add(cb.equal(root.get("assignment").get("createdBy"), createdBy));
//
//            if (assignmentTitle != null && !assignmentTitle.isEmpty()) {
//                predicates.add(cb.like(root.get("assignment").get("title"), "%" + assignmentTitle + "%"));
//            }
//            if (studentName != null && !studentName.isEmpty()) {
//                predicates.add(cb.like(root.get("student").get("username"), "%" + studentName + "%"));
//            }
//            if (className != null) {
//                predicates.add(cb.equal(root.get("className"), className));
//            }
//
//            return cb.and(predicates.toArray(new Predicate[0]));
//        };
//
//        List<SubmittedAssignment> submittedAssignments = submissionsRepository.findAll(spec);
//        List<SubmittedAssignmentDTO> result = new ArrayList<>();
//
//        for (SubmittedAssignment sa : submittedAssignments) {
//            SubmittedAssignmentDTO dto = new SubmittedAssignmentDTO(
//                    sa.getAssignment().getTitle(),
//                    sa.getStudent().getUsername(),
//                    sa.getSubmissionDate(),
//                    sa.getClassName()
//            );
//            result.add(dto);
//        }
//        return result;
//    }


}