package com.leucine.Assignment.controllerImpl;

import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class FacultyControllerImpl implements com.leucine.Assignment.controller.FacultyController {

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
