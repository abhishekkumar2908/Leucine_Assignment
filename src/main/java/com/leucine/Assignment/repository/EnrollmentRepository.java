package com.leucine.Assignment.repository;


import com.leucine.Assignment.dao.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
