package com.leucine.Assignment.controller;


import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.endpoints.AdminApi;
import com.leucine.Assignment.repository.FacultyRepository;
import com.leucine.Assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class AdminController implements AdminApi {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    // CRUD operations for Student
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setStudentId(id);
        return studentRepository.save(student);
    }

    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // CRUD operations for Faculty
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        faculty.setFacultyId(id);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(@PathVariable Long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}
