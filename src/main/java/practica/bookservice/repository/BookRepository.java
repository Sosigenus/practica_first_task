package practica.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practica.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
