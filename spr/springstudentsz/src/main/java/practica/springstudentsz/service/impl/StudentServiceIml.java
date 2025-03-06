package practica.springstudentsz.service.impl;
import org.springframework.web.client.RestTemplate;
import practica.springstudentsz.client.BookClient;
import practica.springstudentsz.dto.BookDTO;
import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.mapper.StudentMapper;
import practica.springstudentsz.model.Student;
import practica.springstudentsz.repository.GroupRepository;
import practica.springstudentsz.repository.StudentRepository;
import practica.springstudentsz.service.StudentService;

import practica.springstudentsz.model.Group;

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
    private final GroupRepository groupRepository;
    private final BookClient bookClient;


    public BookDTO getBookById(Long bookId) {
        return bookClient.getBookById(bookId);
    }

    @Override
    public List<DTOclass> findAllStudent() {
        return repository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    @Override
    public DTOclass saveStudent(DTOclass dto, Long groupId) {
        Student student = studentMapper.toEntity(dto);

        // Ищем группу по ID
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Группа с ID " + groupId + " не найдена!"));

        student.setGroup(group); // Присваиваем студенту группу

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
        // Найдем студента по email
        Student existingStudent = repository.findStudentByEmail(dto.getEmail());

        if (existingStudent == null) {
            throw new IllegalArgumentException("Студент с таким email не найден!");
        }

        // Найдем группу по имени
        Group group = groupRepository.findByName(dto.getGroupName());

        if (group == null) {
            throw new IllegalArgumentException("Группа с таким названием не найдена!");
        }

        // Обновим группу студента
        existingStudent.setGroup(group);

        // Обновим другие данные студента
        studentMapper.updateStudentFromDto(dto, existingStudent);

        // Сохраним обновленного студента
        Student savedStudent = repository.save(existingStudent);

        return studentMapper.toDTO(savedStudent);
    }


    @Override
    @Transactional
    public void deleteStudent(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public Page<DTOclass> findAllStudentsWithFilters(Specification<Student> spec, Pageable pageable) {
        return repository.findAll(spec, pageable).map(studentMapper::toDTO);
    }


}
