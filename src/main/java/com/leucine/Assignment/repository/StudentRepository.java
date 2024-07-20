package com.leucine.Assignment.repository;


import com.leucine.Assignment.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUserId(Long userId);

    List<Student> findByNameContainingAndDepartmentIdAndYear(String name, Long departmentId, Integer year);

    List<Student> findByDepartmentId(Long departmentId);

}
