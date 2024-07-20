package com.leucine.Assignment.repository;


import com.leucine.Assignment.dao.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
