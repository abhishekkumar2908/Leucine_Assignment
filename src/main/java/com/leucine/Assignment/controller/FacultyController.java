package com.leucine.Assignment.controller;


import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dto.FacultyDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/faculty")
public interface FacultyController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/faculty")
    public Faculty addFaculty(@RequestBody FacultyDTO faculty);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/faculty/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/faculty/{id}")
    public void deleteFaculty(@PathVariable Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/faculties")
    public List<Faculty> getAllFaculty() ;

    @GetMapping("/{facultyId}/class-list")
    public Set<Student> getClassList(@PathVariable Long facultyId);

    @PutMapping("/{facultyId}/update-profile")
    public Faculty updateProfile(@PathVariable Long facultyId, @RequestBody Faculty updatedFaculty);

    @GetMapping("/{facultyId}/profile")
    public Faculty getProfile(@PathVariable Long facultyId);
}
