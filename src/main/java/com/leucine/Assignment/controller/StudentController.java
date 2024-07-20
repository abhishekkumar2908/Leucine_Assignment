package com.leucine.Assignment.controller;

import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.endpoints.StudentApi;
import com.leucine.Assignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentController implements StudentApi {
    @Autowired
    private StudentService studentService;

    @Override
    public Student getStudentByUserId(Long userId) {
        return studentService.getStudentByUserId(userId);
    }

    @Override
    public List<Student> searchStudents(String name, Long departmentId, Integer year) {
        return studentService.searchStudents(name, departmentId, year);
    }

    @Override
    public List<Student> getStudentsByDepartmentId(Long departmentId) {
        return studentService.getStudentsByDepartmentId(departmentId);
    }

}
