package com.leucine.Assignment.controllerImpl;

import com.leucine.Assignment.controller.StudentController;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dto.StudentDTO;
import com.leucine.Assignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentControllerImpl implements StudentController {
    @Autowired
    private StudentService studentService;

    @Override
    public Student addStudent(StudentDTO student) {
        return studentService.addStudent(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        return studentService.updateStudent(id, student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentService.deleteStudent(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

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
