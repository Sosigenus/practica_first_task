package practica.springstudentsz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import practica.springstudentsz.dto.DTOclass;
import java.util.List;

public interface StudentService {
    List<DTOclass> findAllStudent();
    DTOclass saveStudent(DTOclass dto);
    DTOclass findByEmail(String email);
    DTOclass updateStudent(DTOclass dto);
    void deleteStudent(String email);
    Page<DTOclass> findAllStudentsWithFilters(String firstName, String lastName, String email, Pageable pageable);
}
