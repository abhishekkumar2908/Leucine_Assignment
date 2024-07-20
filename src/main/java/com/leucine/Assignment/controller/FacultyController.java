package com.leucine.Assignment.controller;


import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dao.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/faculty")
public interface FacultyController {

    @GetMapping("/{facultyId}/class-list")
    public Set<Student> getClassList(@PathVariable Long facultyId);

    @PutMapping("/{facultyId}/update-profile")
    public Faculty updateProfile(@PathVariable Long facultyId, @RequestBody Faculty updatedFaculty);

    @GetMapping("/{facultyId}/profile")
    public Faculty getProfile(@PathVariable Long facultyId);
}
