package practica.springstudentsz.controller;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public List<DTOclass> findAllStudents() {
        return service.findAllStudent();
    }

    @PostMapping("/save_student")
    public DTOclass saveStudent(@RequestBody DTOclass dto) {
        return service.saveStudent(dto);
    }

    @GetMapping("/{email}")
    public DTOclass findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PutMapping("/update_student")
    public DTOclass updateStudent(@RequestBody DTOclass dto) {
        return service.updateStudent(dto);
    }

    @DeleteMapping("/delete_student/{email}")
    public void deleteStudent(@PathVariable String email) {
        service.deleteStudent(email);
    }
}

