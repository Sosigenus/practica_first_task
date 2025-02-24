package practica.springstudentsz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import practica.springstudentsz.dto.DTOclass;
import practica.springstudentsz.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "group.name", target = "groupName")
    DTOclass toDTO(Student student);



    @Mapping(target = "group", ignore = true)
    Student toEntity(DTOclass dto);


    void updateStudentFromDto(DTOclass dto, @MappingTarget Student student);
}

