package com.leucine.Assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudentByUserId(Long userId) {
        return studentRepository.findByUserId(userId);
    }

    public List<Student> searchStudents(String name, Long departmentId, Integer year) {
        return studentRepository.findByNameContainingAndDepartmentIdAndYear(name, departmentId, year);
    }

    public List<Student> getStudentsByDepartmentId(Long departmentId) {
        return studentRepository.findByDepartmentId(departmentId);
    }
}
