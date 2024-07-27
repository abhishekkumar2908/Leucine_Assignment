package com.leucine.Assignment.repository;

import com.leucine.Assignment.dao.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentsRepository extends JpaRepository<Assignments, Long> {
}