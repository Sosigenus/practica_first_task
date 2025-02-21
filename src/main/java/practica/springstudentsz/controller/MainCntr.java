package practica.springstudentsz.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import practica.springstudentsz.dto.DTOclass;

import practica.springstudentsz.service.StudentService;


import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
@Tag(name = "Students")
public class MainCntr {
    private final StudentService service;

    @Operation(
            summary = "Students info",
            description = "description students"
    )
    @GetMapping
    public Page<DTOclass> findAllStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            Pageable pageable) {
        return service.findAllStudentsWithFilters(firstName, lastName, email, pageable);
    }
    @Operation(summary = "Получить всех студентов без фильтров")
    @GetMapping("/all")
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

