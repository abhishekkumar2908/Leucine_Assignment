package com.leucine.Assignment.repository;

import com.leucine.Assignment.dao.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.Optional;

public interface AssignmentsRepository extends JpaRepository<Assignments, Long> {
    Optional<Assignments> findById(Long id);

    Optional<Assignments> findByTitle(String title);

    Optional<Assignments> findByCreatedBy(Long createdBy);

}