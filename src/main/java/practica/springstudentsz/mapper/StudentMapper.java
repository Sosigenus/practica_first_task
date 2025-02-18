package practica.springstudentsz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    DTOclass toDTO(Student student);
    Student toEntity(DTOclass dto);

}

