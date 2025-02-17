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

    //write, because i have more two bean
    //@Autowired
    @Qualifier("studentMapperImpl")
    private StudentMapper mapper;
    //private final StudentMapper mapper;


    @GetMapping
    public List<DTOclass> findAllStudents() {
        return service.findAllStudent()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/save_student")
    public DTOclass saveStudent(@RequestBody DTOclass dto) {
        Student student = mapper.toEntity(dto);
        return mapper.toDTO(service.saveStudent(student));
    }


    @GetMapping("/{email}")
    public DTOclass findByEmail(@PathVariable String email) {
        return mapper.toDTO(service.findByEmail(email));
    }
    // /api/v1/students/oleg12@gmail.com

    @PutMapping("/update_student")
    public DTOclass updateStudent(@RequestBody DTOclass dto) {
        Student existingStudent = service.findByEmail(dto.getEmail());

        if (existingStudent == null) {
            throw new IllegalArgumentException("Студент с таким email не найден!");
        }

        existingStudent.setFirstName(dto.getFirstName());
        existingStudent.setLastName(dto.getLastName());
        existingStudent.setDateOfBirth(dto.getDateOfBirth());

        Student updatedStudent = service.updateStudent(existingStudent);
        return mapper.toDTO(updatedStudent);
    }


    @DeleteMapping("/delete_student/{email}")
    public void deleteStudent(@PathVariable String email) {
        service.deleteStudent(email);
    }
}
