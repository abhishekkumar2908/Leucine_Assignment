package com.leucine.Assignment.controller;


import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dto.StudentDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/api/students", produces = "application/json", consumes = "application/json")
public interface StudentController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/student")
    Student addStudent(@RequestBody StudentDTO student);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/student/{id}")
    Student updateStudent(@PathVariable Long id, @RequestBody Student student);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/student/{id}")
    void deleteStudent(@PathVariable Long id) throws Throwable;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    List<Student> getAllStudents();

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    Student getStudentByUserId(Long userId);

    @GetMapping("/search/students")
    List<Student> searchStudents (@RequestParam String name, @RequestParam Long departmentId, @RequestParam Integer year);

    @GetMapping("/students/department/{departmentId}")
    List<Student> getStudentsByDepartmentId(@PathVariable Long departmentId);
}
