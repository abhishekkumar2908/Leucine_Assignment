package com.leucine.Assignment.repository;

import com.leucine.Assignment.dao.Assignments;
import com.leucine.Assignment.enums.ClassName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.Optional;

public interface AssignmentsRepository extends JpaRepository<Assignments, Long> {
    Optional<Assignments> findById(Long id);

    Optional<Assignments> findByTitle(String title);

    Optional<Assignments> findByCreatedBy(Long createdBy);

    @Query("SELECT a FROM Assignments a WHERE a.className = :className AND a.createdBy = :userId")
    Object findByClassName(ClassName className, Long userId);
}