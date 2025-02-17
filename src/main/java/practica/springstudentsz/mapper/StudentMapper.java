package practica.springstudentsz.mapper;

import practica.springstudentsz.model.Student;
import practica.springstudentsz.dto.DTOclass;

public class StudentMapper {
    public static DTOclass toDTO(Student student) {
        DTOclass dto = new DTOclass();
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setEmail(student.getEmail());
        return dto;
    }

    public static Student toEntity(DTOclass dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setEmail(dto.getEmail());
        return student;
    }
}
