package com.leucine.Assignment.endpoints;

import com.leucine.Assignment.dao.Student;
import com.leucine.Assignment.dao.Faculty;
import com.leucine.Assignment.dto.FacultyDTO;
import com.leucine.Assignment.dto.StudentDTO;
import com.leucine.Assignment.repository.StudentRepository;
import com.leucine.Assignment.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public interface AdminApi {



    @PostMapping("/student")
    public Student addStudent(@RequestBody StudentDTO student);

    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student);

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id);

    @GetMapping("/students")
    public List<Student> getAllStudents();

    @PostMapping("/faculty")
    public Faculty addFaculty(@RequestBody FacultyDTO faculty);

    @PutMapping("/faculty/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty);

    @DeleteMapping("/faculty/{id}")
    public void deleteFaculty(@PathVariable Long id);

    @GetMapping("/faculties")
    public List<Faculty> getAllFaculty() ;
}
