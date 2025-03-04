package practica.bookservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import practica.bookservice.dto.BookDTO;
import practica.bookservice.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(Book book);
    Book toEntity(BookDTO dto);
    void updateBookFromDto(BookDTO dto, @MappingTarget Book book);
}