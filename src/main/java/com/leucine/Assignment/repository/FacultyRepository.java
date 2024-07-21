package com.leucine.Assignment.repository;


import com.leucine.Assignment.dao.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByDepartmentId(Long departmentId);

    Optional<Faculty> findByFacultyId(Long facultyId);
}
