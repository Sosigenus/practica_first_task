package practica.springstudentsz.controller;

import lombok.*;
import org.springframework.web.bind.annotation.*;
import practica.springstudentsz.model.Student;
import practica.springstudentsz.service.StudentService;

import java.util.List;
@RestController
@RequestMapping("/api/v1/students")


@AllArgsConstructor
public class MainCntr {

    private final StudentService service;

    @GetMapping
    public List<Student> findAllStudent()
    {
        return service.findAllStudent();
    }
    @PostMapping("save_student")
    public Student saveStudent(@RequestBody Student student){
        return service.saveStudent(student);
    }
    @GetMapping("/{email}")
    public Student findByEmail(@PathVariable("email") String email){
        return service.findByEmail(email);
    }
    @PutMapping("update_student")
    public Student updateStudent(Student student){
        return service.updateStudent(student);
    }
    @DeleteMapping("delete_student/{email}")
    public void deleteStudent(@PathVariable("email") String email){
        service.deleteStudent(email);
    }
}
