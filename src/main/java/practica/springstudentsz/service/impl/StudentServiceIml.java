package practica.springstudentsz.service.impl;
import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.mapper.StudentMapper;
import practica.springstudentsz.model.Student;
import practica.springstudentsz.repository.StudentRepository;
import practica.springstudentsz.service.StudentService;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class StudentServiceIml implements StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    @Override
    public List<DTOclass> findAllStudent() {
        return repository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    @Override
    public DTOclass saveStudent(DTOclass dto) {
        Student student = studentMapper.toEntity(dto);
        Student saved = repository.save(student);
        return studentMapper.toDTO(saved);
    }

    @Override
    public DTOclass findByEmail(String email) {
        Student student = repository.findStudentByEmail(email);
        return studentMapper.toDTO(student);
    }

    @Override
    public DTOclass updateStudent(DTOclass dto) {
        Student existingStudent = repository.findStudentByEmail(dto.getEmail());
        if (existingStudent == null) {
            throw new IllegalArgumentException("Студент с таким email не найден!");
        }


        Student updatedStudent = studentMapper.toEntity(dto);


        Student savedStudent = repository.save(updatedStudent);
        return studentMapper.toDTO(savedStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(String email) {
        repository.deleteByEmail(email);
    }
}
