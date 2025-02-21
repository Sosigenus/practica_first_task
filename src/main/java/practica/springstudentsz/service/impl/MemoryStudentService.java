package practica.springstudentsz.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.mapper.StudentMapper;
import practica.springstudentsz.model.Student;
import practica.springstudentsz.repository.MemoryStudentFun;
import practica.springstudentsz.service.StudentService;
import org.springframework.data.domain.PageImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemoryStudentService implements StudentService {

    private final MemoryStudentFun repository;
    private final StudentMapper studentMapper;

    @Override
    public Page<DTOclass> findAllStudentsWithFilters(String firstName, String lastName, String email, Pageable pageable) {
        List<DTOclass> filteredStudents = repository.findAllStudent().stream()
                .filter(student -> (firstName == null || student.getFirstName().contains(firstName)))
                .filter(student -> (lastName == null || student.getLastName().contains(lastName)))
                .filter(student -> (email == null || student.getEmail().contains(email)))
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredStudents.size());

        List<DTOclass> pageContent = filteredStudents.subList(start, end);
        return new PageImpl<>(pageContent, pageable, filteredStudents.size());
    }



    @Override
    public List<DTOclass> findAllStudent() {
        return repository.findAllStudent()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DTOclass saveStudent(DTOclass dto) {
        Student student = studentMapper.toEntity(dto);
        Student saved = repository.saveStudent(student);
        return studentMapper.toDTO(saved);
    }

    @Override
    public DTOclass findByEmail(String email) {
        Student student = repository.findByEmail(email);
        return student != null ? studentMapper.toDTO(student) : null;
    }

    @Override
    public DTOclass updateStudent(DTOclass dto) {
        Student student = repository.findByEmail(dto.getEmail());
        if (student == null) {
            throw new IllegalArgumentException("Студент с таким email не найден!");
        }

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDateOfBirth(dto.getDateOfBirth());

        Student updated = repository.updateStudent(student);
        return studentMapper.toDTO(updated);
    }

    @Override
    public void deleteStudent(String email) {
        repository.deleteStudent(email);
    }
}
