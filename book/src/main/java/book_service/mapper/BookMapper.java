package book_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import book_service.dto.BookDTO;
import book_service.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(Book book);
    Book toEntity(BookDTO dto);
    void updateBookFromDto(BookDTO dto, @MappingTarget Book book);
}