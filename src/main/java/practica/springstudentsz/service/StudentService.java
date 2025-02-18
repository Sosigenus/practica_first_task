package practica.springstudentsz.service;

import practica.springstudentsz.dto.DTOclass;
import java.util.List;

public interface StudentService {
    List<DTOclass> findAllStudent();
    DTOclass saveStudent(DTOclass dto);
    DTOclass findByEmail(String email);
    DTOclass updateStudent(DTOclass dto);
    void deleteStudent(String email);
}
