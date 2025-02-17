package practica.springstudentsz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
//    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    DTOclass toDTO(Student student);
    Student toEntity(DTOclass dto);
}
