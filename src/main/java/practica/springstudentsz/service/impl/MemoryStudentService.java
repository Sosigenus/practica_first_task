/*
package practica.springstudentsz.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.mapper.StudentMapper;
import practica.springstudentsz.model.Student;
import practica.springstudentsz.repository.MemoryStudentFun;
import practica.springstudentsz.repository.StudentRepository;
import practica.springstudentsz.service.StudentService;
import org.springframework.data.domain.PageImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemoryStudentService implements StudentService {

    private final StudentRepository repository; // Используем репозиторий для базы данных
    private final StudentMapper studentMapper;

    @Override
    public Page<DTOclass> findAllStudentsWithFilters(Specification<Student> spec, Pageable pageable) {
        // Используем findAll с Specification для работы с фильтрацией
        Page<Student> studentPage = repository.findAll(spec, pageable);

        // Преобразуем страницу студентов в DTO
        List<DTOclass> studentDtos = studentPage.getContent().stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(studentDtos, pageable, studentPage.getTotalElements());
    }

    @Override
    public List<DTOclass> findAllStudent() {
        return repository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DTOclass saveStudent(DTOclass dto, Long groupId) {
        return saveStudent(dto); // groupId пока игнорируем
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
    public void deleteStudent(String email) {
        repository.deleteByEmail(email);
    }
}
*/
