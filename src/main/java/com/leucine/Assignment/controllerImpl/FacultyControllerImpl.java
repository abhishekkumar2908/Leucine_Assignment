package com.leucine.Assignment.controllerImpl;

import com.leucine.Assignment.controller.FacultyController;
import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dto.FacultyDTO;
import com.leucine.Assignment.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class FacultyControllerImpl implements FacultyController {

    @Autowired
    private FacultyService facultyService;

    @Override
    public Faculty addFaculty(FacultyDTO faculty) {
        return facultyService.addFaculty(faculty);
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyService.deleteFaculty(id);
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

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
