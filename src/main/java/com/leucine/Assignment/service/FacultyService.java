package com.leucine.Assignment.service;

import com.leucine.Assignment.UserRole;
import com.leucine.Assignment.dao.Course;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.dto.FacultyDTO;
import com.leucine.Assignment.repository.FacultyRepository;
import com.leucine.Assignment.repository.StudentRepository;
import com.leucine.Assignment.repository.CourseRepository;

import com.leucine.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leucine.Assignment.dao.Faculty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public Faculty addFaculty(@RequestBody FacultyDTO facultyDTO) {
        User user = User.builder()
                .username(facultyDTO.getUsername())
                .email(facultyDTO.getEmail())
                .password(facultyDTO.getPassword())
                .role(UserRole.FACULTY)
                .name(facultyDTO.getName())
                .phone(facultyDTO.getPhone())
                .build();

        Faculty faculty = new Faculty();
        faculty.setDepartmentId(facultyDTO.getDepartmentId());
        faculty.setPhoto(facultyDTO.getPhoto());
        faculty.setOfficeHours(facultyDTO.getOfficeHours());

        User savedUser = userRepository.save(user);
        faculty.setUser(savedUser);

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

    public Set<Student> getClassList(Long facultyId) {
        List<Course> courses = courseRepository.findByFaculty_FacultyId(facultyId);
        Set<Student> students = new HashSet<>();
        for (Course course : courses) {
            students.addAll(course.getStudents());
        }
        return students;
    }

    public Faculty updateProfile(Long facultyId, Faculty updatedFaculty) {
        Faculty faculty = facultyRepository.findByFacultyId(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        User user = faculty.getUser();
        user.setEmail(updatedFaculty.getUser().getEmail());
        user.setPhone(updatedFaculty.getUser().getPhone());

        faculty.setOfficeHours(updatedFaculty.getOfficeHours());


        return facultyRepository.save(faculty);
    }

    public Faculty getProfile(Long facultyId) {
        return facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
    }
}
