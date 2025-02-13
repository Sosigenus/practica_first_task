package practica.springstudentsz.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import practica.springstudentsz.model.Student;
import practica.springstudentsz.repository.MemoryStudentFun;
import practica.springstudentsz.service.StudentService;

import java.util.List;
@Service
@AllArgsConstructor
public class MemoryStudentService implements StudentService {
    private final MemoryStudentFun repository;
    @Override
    public List<Student> findAllStudent() {
        return repository.findAllStudent();
    }
    @Override
    public Student saveStudent(Student student)
    {
        return repository.saveStudent(student);
    }
    @Override
    public Student findByEmail(String email){
        return repository.findByEmail(email);
    }
    @Override
    public Student updateStudent(Student student){
        return repository.updateStudent(student);
    }
    @Override
    public void deleteStudent(String email){
        repository.deleteStudent(email);
    }

}
