package com.leucine.Assignment.repository;


import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUserRole(UserRole userRole);

    Long getUserIdByUsername(String username);
}
