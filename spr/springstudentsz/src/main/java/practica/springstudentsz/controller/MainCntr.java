package practica.springstudentsz.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import practica.springstudentsz.dto.BookDTO;
import practica.springstudentsz.dto.DTOclass;

import practica.springstudentsz.model.Student;
import practica.springstudentsz.service.StudentService;

import org.springframework.web.bind.annotation.*;


import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.*;
import org.springframework.data.jpa.domain.Specification;

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
            @And({
                    @Spec(path = "firstName", spec = Like.class),
                    @Spec(path = "lastName", spec = Like.class),
                    @Spec(path = "email", spec = Like.class),
                    @Spec(path = "group.name", spec = Like.class) // Фильтр по названию группы
            }) Specification<Student> spec,
            Pageable pageable
    ) {
        return service.findAllStudentsWithFilters(spec, pageable);
    }


    @Operation(summary = "Получить всех студентов без фильтров")
    @GetMapping("/all")
    public List<DTOclass> findAllStudents() {
        return service.findAllStudent();
    }


    @PostMapping("/save_student/{groupId}")
    public DTOclass saveStudent(@RequestBody DTOclass dto, @PathVariable Long groupId) {
        return service.saveStudent(dto, groupId);
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

    @Operation(summary = "Получить информацию о книге по ID")
    @GetMapping("/book/{bookId}")
    public BookDTO getBookById(@PathVariable Long bookId) {
        return service.getBookById(bookId);
    }

}

