package practica.springstudentsz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    DTOclass toDTO(Student student);
    Student toEntity(DTOclass dto);

    void updateStudentFromDto(DTOclass dto, @MappingTarget Student student);
}

