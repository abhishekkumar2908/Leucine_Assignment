package com.leucine.Assignment.repository;

import com.leucine.Assignment.dao.SubmittedAssignment;
import com.leucine.Assignment.dto.SubmittedAssignmentDTO;
import com.leucine.Assignment.enums.ClassName;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.hibernate.validator.constraints.UUID;
@Repository
public interface SubmittedAssignmentRepository extends JpaRepository<SubmittedAssignment, Long> {
    List<SubmittedAssignment> findAllByAssignmentIdIn(List<Long> assignmentIds);
}