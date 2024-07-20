package com.leucine.Assignment.controller;

import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.endpoints.FacultyApi;
import com.leucine.Assignment.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class FacultyController implements FacultyApi {

    @Autowired
    private FacultyService facultyService;

    @Override
    public Set<Student> getClassList(Long facultyId) {
        return facultyService.getClassList(facultyId);
    }

    @Override
    public Faculty updateProfile(Long facultyId, Faculty updatedFaculty) {
        return facultyService.updateProfile(facultyId, updatedFaculty);
    }

    @Override
    public Faculty getProfile(Long facultyId) {
        return facultyService.getProfile(facultyId);
    }
}
