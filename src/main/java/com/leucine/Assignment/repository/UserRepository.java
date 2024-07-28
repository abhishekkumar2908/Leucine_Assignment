package com.leucine.Assignment.repository;


import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUserRole(UserRole userRole);

    @Query("SELECT u.userId FROM User u WHERE u.username = :username")
    Long findUserIdByUsername(String username);
}
