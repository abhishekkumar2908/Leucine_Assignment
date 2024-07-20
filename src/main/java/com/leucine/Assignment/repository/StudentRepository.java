package com.leucine.Assignment.repository;


import com.leucine.Assignment.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUserId(Long userId);

    @Query("SELECT s FROM Student s WHERE s.user.name LIKE %:name% AND s.departmentId = :departmentId AND s.year = :year")
    List<Student> findByNameContainingAndDepartmentIdAndYear(String name, Long departmentId, Integer year);

    List<Student> findByDepartmentId(Long departmentId);

}
