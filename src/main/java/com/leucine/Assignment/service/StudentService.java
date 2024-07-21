package com.leucine.Assignment.service;

import com.leucine.Assignment.UserRole;
import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.dto.StudentDTO;
import com.leucine.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.repository.StudentRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;


    public Student addStudent(@RequestBody StudentDTO studentDTO) {
        User user = User.builder()
                .username(studentDTO.getUsername())
                .email(studentDTO.getEmail())
                .password(studentDTO.getPassword())
                .role(UserRole.STUDENT)
                .name(studentDTO.getName())
                .phone(studentDTO.getPhone())
                .build();
        User savedUser = userRepository.save(user);

        Student student = new Student();
        student.setUser(savedUser);
        student.setDepartmentId(studentDTO.getDepartmentId());
        student.setPhoto(studentDTO.getPhoto());
        student.setYear(Integer.parseInt(studentDTO.getYear()));

        return studentRepository.save(student);
    }

    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        User existingUser = existingStudent.getUser();
        existingUser.setUsername(studentDetails.getUser().getUsername());
        existingUser.setEmail(studentDetails.getUser().getEmail());
        existingUser.setPassword(studentDetails.getUser().getPassword());
        existingUser.setRole(UserRole.STUDENT);
        existingUser.setName(studentDetails.getUser().getName());
        existingUser.setPhone(studentDetails.getUser().getPhone());
        userRepository.save(existingUser); // Save the updated User

        existingStudent.setDepartmentId(studentDetails.getDepartmentId());
        existingStudent.setPhoto(studentDetails.getPhoto());
        existingStudent.setYear(studentDetails.getYear());
        // Save the updated Student
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Delete the Student entity
        studentRepository.deleteById(id);

        // Delete the associated User entity
        userRepository.deleteById(student.getUser().getUserId());
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByUserId(Long userId) {
        return studentRepository.findByStudentId(userId);
    }

    public List<Student> searchStudents(String name, Long departmentId, Integer year) {
        return studentRepository.findByNameContainingAndDepartmentIdAndYear(name, departmentId, year);
    }

    public List<Student> getStudentsByDepartmentId(Long departmentId) {
        return studentRepository.findByDepartmentId(departmentId);
    }
}
