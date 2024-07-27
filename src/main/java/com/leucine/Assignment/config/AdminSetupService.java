package com.leucine.Assignment.config;

import com.leucine.Assignment.enums.UserRole;
import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminSetupService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void setupUsers() {
//        createAdmin();
        createTeacher();
        createStudents();
    }


    private void createTeacher() {
        Boolean isTeacherExists = userRepository.existsByUserRole(UserRole.TEACHER);
        if (!isTeacherExists) {
            User user = User.builder()
                    .username("teacher")
                    .password(passwordEncoder.encode("teacher1234"))
                    .userRole(UserRole.TEACHER)
                    .build();
            userRepository.save(user);
        } else {
            System.out.println("Teacher already exists");
        }
    }

    private void createStudents() {
        Boolean isStudentExists = userRepository.existsByUserRole(UserRole.STUDENT);
        if (!isStudentExists) {
            User user = User.builder()
                    .username("student")
                    .password(passwordEncoder.encode("student1234"))
                    .userRole(UserRole.STUDENT)
                    .build();
            userRepository.save(user);
        } else {
            System.out.println("Student already exists");
        }
    }
}