package practica.bookservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import practica.bookservice.dto.BookDTO;
import practica.bookservice.event.BookEvent;
import practica.bookservice.kafka.KafkaProducer;
import practica.bookservice.mapper.BookMapper;
import practica.bookservice.model.Book;
import practica.bookservice.repository.BookRepository;
import practica.bookservice.service.BookService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final KafkaProducer kafkaProducer;


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

        // Отправка уведомления в Kafka
        BookEvent event = new BookEvent();
        event.setBookId(savedBook.getId());
        event.setMessage("Книга добавлена: " + savedBook.getTitle());
        kafkaProducer.sendBookNotification(event);

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
