package com.leucine.Assignment.repository;


import com.leucine.Assignment.dao.Admin;
import com.leucine.Assignment.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUser_Username(String username);

    Boolean existsByUser_Username(String admin);
}
