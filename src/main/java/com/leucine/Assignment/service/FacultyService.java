package com.leucine.Assignment.service;

import com.leucine.Assignment.dao.Course;
import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.repository.FacultyRepository;
import com.leucine.Assignment.repository.StudentRepository;
import com.leucine.Assignment.repository.CourseRepository;

import com.leucine.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leucine.Assignment.dao.Faculty;

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
