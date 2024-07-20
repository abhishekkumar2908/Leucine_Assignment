package com.leucine.Assignment.controller;


import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dto.StudentDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public interface StudentController {


    @PostMapping("/student")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Student addStudent(@RequestBody StudentDTO student);

    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student);

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id);

    @GetMapping("/students")
    public List<Student> getAllStudents();

    @GetMapping("/{userId}")
    Student getStudentByUserId(Long userId);

    @GetMapping("/search/students")
    public List<Student> searchStudents
            (@RequestParam String name, @RequestParam Long departmentId, @RequestParam Integer year);

    @GetMapping("/students/department/{departmentId}")
    public List<Student> getStudentsByDepartmentId(@PathVariable Long departmentId);
}
