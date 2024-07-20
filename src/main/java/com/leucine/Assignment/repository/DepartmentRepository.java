package com.leucine.Assignment.repository;



import com.leucine.Assignment.dao.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
