package book_service.service.impl;

import book_service.dto.BookNotificationDTO;
import book_service.kafka.BookKafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import book_service.dto.BookDTO;

import book_service.mapper.BookMapper;
import book_service.model.Book;
import book_service.repository.BookRepository;
import book_service.service.BookService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookKafkaProducer bookKafkaProducer;
    //private final KafkaProducer kafkaProducer;


    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDTO).toList();
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Книга не найдена"));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);

        BookNotificationDTO notificationDTO = BookNotificationDTO.builder()
                .title(savedBook.getTitle())
                .author(savedBook.getAuthor())
                .message("New book")
                .build();

        bookKafkaProducer.sendNotification(notificationDTO);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Книга не найдена"));
        bookMapper.updateBookFromDto(bookDTO, book);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
