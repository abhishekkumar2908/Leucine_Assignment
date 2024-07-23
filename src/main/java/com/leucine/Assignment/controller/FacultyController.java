package com.leucine.Assignment.controller;


import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dto.FacultyDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/faculties")
public interface FacultyController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/faculty")
    Faculty addFaculty(@RequestBody FacultyDTO faculty);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/faculty/{id}")
    Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/faculty/{id}")
    void deleteFaculty(@PathVariable Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/faculties")
    List<Faculty> getAllFaculty() ;

    @GetMapping("/{facultyId}/class-list")
    Set<Student> getClassList(@PathVariable Long facultyId);

    @PutMapping("/{facultyId}/update-profile")
    Faculty updateProfile(@PathVariable Long facultyId, @RequestBody Faculty updatedFaculty);

    @GetMapping("/{facultyId}/profile")
    Faculty getProfile(@PathVariable Long facultyId);
}
