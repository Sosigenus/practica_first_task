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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;



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

        studentMapper.updateStudentFromDto(dto, existingStudent);
        Student savedStudent = repository.save(existingStudent);

        return studentMapper.toDTO(savedStudent);
    }


    @Override
    @Transactional
    public void deleteStudent(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public Page<DTOclass> findAllStudentsWithFilters(String firstName, String lastName, String email, Pageable pageable) {
        Specification<Student> spec = Specification.where(null);

        if (firstName != null && !firstName.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
        }

        if (lastName != null && !lastName.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
        }

        if (email != null && !email.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("email"), "%" + email + "%"));
        }

        return repository.findAll(spec, pageable).map(studentMapper::toDTO);
    }
}
