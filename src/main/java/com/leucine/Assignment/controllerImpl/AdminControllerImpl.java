package com.leucine.Assignment.controllerImpl;


import com.leucine.Assignment.UserRole;
import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.dto.FacultyDTO;
import com.leucine.Assignment.repository.FacultyRepository;
import com.leucine.Assignment.repository.StudentRepository;
import com.leucine.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class AdminControllerImpl implements com.leucine.Assignment.controller.AdminController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    // CRUD operations for Student

    // CRUD operations for Faculty
    @PostMapping("/faculties")
    public Faculty addFaculty(@RequestBody FacultyDTO facultyDTO) {
        User user = User.builder()
                .username(facultyDTO.getUsername())
                .email(facultyDTO.getEmail())
                .password(facultyDTO.getPassword())
                .role(UserRole.FACULTY)
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
        existingUser.setRole(UserRole.FACULTY);
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
