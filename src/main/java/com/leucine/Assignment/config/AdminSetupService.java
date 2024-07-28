package com.leucine.Assignment.config;

import com.leucine.Assignment.dao.Classes;
import com.leucine.Assignment.enums.ClassName;
import com.leucine.Assignment.enums.UserRole;
import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.repository.ClassRepository;
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

    @Autowired
    private ClassRepository classRepository;

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
                    .username("teacher1")
                    .password("t1234")
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
                    .username("student1")
                    .password("s1234")
                    .userRole(UserRole.STUDENT)
                    .build();
            userRepository.save(user);
            Classes classes = Classes.builder()
                    .studentId(user.getUserId())
                    .className(ClassName.XI)
                    .build();
            classRepository.save(classes);
        } else {
            System.out.println("Student already exists");
        }
    }
}