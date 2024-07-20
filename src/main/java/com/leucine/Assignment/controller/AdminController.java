package com.leucine.Assignment.controller;

import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dto.FacultyDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public interface AdminController {





    @PostMapping("/faculty")
    public Faculty addFaculty(@RequestBody FacultyDTO faculty);

    @PutMapping("/faculty/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty);

    @DeleteMapping("/faculty/{id}")
    public void deleteFaculty(@PathVariable Long id);

    @GetMapping("/faculties")
    public List<Faculty> getAllFaculty() ;
}
