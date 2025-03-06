package practica.springstudentsz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import practica.springstudentsz.dto.BookDTO;
import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.model.Student;

import java.util.List;

public interface StudentService {
    List<DTOclass> findAllStudent();
    DTOclass saveStudent(DTOclass dto, Long groupId);
    DTOclass findByEmail(String email);
    DTOclass updateStudent(DTOclass dto);
    void deleteStudent(String email);
    Page<DTOclass> findAllStudentsWithFilters(Specification<Student> spec, Pageable pageable);
    BookDTO getBookById(Long bookId);

}
