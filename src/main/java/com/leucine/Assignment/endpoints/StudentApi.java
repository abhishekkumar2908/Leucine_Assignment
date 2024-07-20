package com.leucine.Assignment.endpoints;


import com.leucine.Assignment.dao.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public interface StudentApi {


    @GetMapping("/{userId}")
    Student getStudentByUserId(Long userId);

    @GetMapping("/search/students")
    public List<Student> searchStudents
            (@RequestParam String name, @RequestParam Long departmentId, @RequestParam Integer year);

    @GetMapping("/students/department/{departmentId}")
    public List<Student> getStudentsByDepartmentId(@PathVariable Long departmentId);
}
