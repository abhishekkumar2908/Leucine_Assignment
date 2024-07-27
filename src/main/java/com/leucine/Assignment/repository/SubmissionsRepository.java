package com.leucine.Assignment.repository;

import com.leucine.Assignment.dao.Submissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionsRepository extends JpaRepository<Submissions, Long> {
}