package com.leucine.Assignment.controller;


import com.leucine.Assignment.UserRole;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.dto.FacultyDTO;
import com.leucine.Assignment.dto.StudentDTO;
import com.leucine.Assignment.endpoints.AdminApi;
import com.leucine.Assignment.repository.FacultyRepository;
import com.leucine.Assignment.repository.StudentRepository;
import com.leucine.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class AdminController implements AdminApi {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    // CRUD operations for Student
    @PostMapping("/students")
    public Student addStudent(@RequestBody StudentDTO studentDTO) {
        User user = User.builder()
                .username(studentDTO.getUsername())
                .email(studentDTO.getEmail())
                .password(studentDTO.getPassword())
                .role(UserRole.valueOf(studentDTO.getRole()))
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
        existingUser.setRole(studentDetails.getUser().getRole());
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

    // CRUD operations for Faculty
    @PostMapping("/faculties")
    public Faculty addFaculty(@RequestBody FacultyDTO facultyDTO) {
        User user = User.builder()
                .username(facultyDTO.getUsername())
                .email(facultyDTO.getEmail())
                .password(facultyDTO.getPassword())
                .role(UserRole.valueOf(facultyDTO.getRole()))
                .name(facultyDTO.getName())
                .phone(facultyDTO.getPhone())
                .build();
        User savedUser = userRepository.save(user);

        Faculty faculty = new Faculty();
        faculty.setUser(savedUser);
        faculty.setDepartmentId(facultyDTO.getDepartmentId());
        faculty.setPhoto(facultyDTO.getPhoto());
        faculty.setOfficeHours(facultyDTO.getOfficeHours());

        return facultyRepository.save(faculty);
    }
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty facultyDetails) {
        Faculty existingFaculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        User existingUser = existingFaculty.getUser();
        existingUser.setUsername(facultyDetails.getUser().getUsername());
        existingUser.setEmail(facultyDetails.getUser().getEmail());
        existingUser.setPassword(facultyDetails.getUser().getPassword());
        existingUser.setRole(facultyDetails.getUser().getRole());
        existingUser.setName(facultyDetails.getUser().getName());
        existingUser.setPhone(facultyDetails.getUser().getPhone());
        userRepository.save(existingUser); // Save the updated User

        existingFaculty.setDepartmentId(facultyDetails.getDepartmentId());
        existingFaculty.setPhoto(facultyDetails.getPhoto());
        existingFaculty.setOfficeHours(facultyDetails.getOfficeHours());
        // Save the updated Faculty
        return facultyRepository.save(existingFaculty);
    }
    public void deleteFaculty(@PathVariable Long id) {
        // Fetch the Faculty entity to get the associated User
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        // Delete the Faculty entity
        facultyRepository.deleteById(id);

        // Delete the associated User entity
        userRepository.deleteById(faculty.getUser().getUserId());
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}
