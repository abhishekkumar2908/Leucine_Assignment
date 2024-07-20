package com.leucine.Assignment.controller;


import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dto.FacultyDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/faculty")
public interface FacultyController {

    @PostMapping("/faculty")
    public Faculty addFaculty(@RequestBody FacultyDTO faculty);

    @PutMapping("/faculty/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty);

    @DeleteMapping("/faculty/{id}")
    public void deleteFaculty(@PathVariable Long id);

    @GetMapping("/faculties")
    public List<Faculty> getAllFaculty() ;

    @GetMapping("/{facultyId}/class-list")
    public Set<Student> getClassList(@PathVariable Long facultyId);

    @PutMapping("/{facultyId}/update-profile")
    public Faculty updateProfile(@PathVariable Long facultyId, @RequestBody Faculty updatedFaculty);

    @GetMapping("/{facultyId}/profile")
    public Faculty getProfile(@PathVariable Long facultyId);
}
