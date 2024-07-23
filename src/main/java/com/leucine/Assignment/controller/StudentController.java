package com.leucine.Assignment.controller;


import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/api/students", produces = "application/json", consumes = "application/json")
public interface StudentController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/student")
    public ResponseEntity<String> addStudent(@RequestBody StudentDTO student);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public List<Student> getAllStudents();

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    Student getStudentByUserId(Long userId);

    @GetMapping("/search/students")
    public List<Student> searchStudents
            (@RequestParam String name, @RequestParam Long departmentId, @RequestParam Integer year);

    @GetMapping("/students/department/{departmentId}")
    public List<Student> getStudentsByDepartmentId(@PathVariable Long departmentId);
}
