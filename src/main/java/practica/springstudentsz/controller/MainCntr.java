package practica.springstudentsz.controller;
import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.mapper.StudentMapper;
import practica.springstudentsz.model.Student;
import practica.springstudentsz.service.StudentService;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class MainCntr {
    private final StudentService service;

    @GetMapping
    public List<DTOclass> findAllStudent() {
        List<Student> students = service.findAllStudent();
        return students.stream().map(StudentMapper::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/save_student")
    public DTOclass saveStudent(@RequestBody DTOclass DTOclass) {
        Student student = StudentMapper.toEntity(DTOclass);
        Student savedStudent = service.saveStudent(student);
        return StudentMapper.toDTO(savedStudent);
    }


    @GetMapping("/{email}")
    public DTOclass findByEmail(@PathVariable String email) {
        Student student = service.findByEmail(email);
        return StudentMapper.toDTO(student);
    }
    // /api/v1/students/oleg12@gmail.com

    @PutMapping("update_student")
    public DTOclass updateStudent(@RequestBody DTOclass DTOclass) {
        Student student = StudentMapper.toEntity(DTOclass);
        Student updatedStudent = service.updateStudent(student);
        return StudentMapper.toDTO(updatedStudent);
    }

    @DeleteMapping("delete_student/{email}")
    public void deleteStudent(@PathVariable String email) {
        service.deleteStudent(email);
    }
}
