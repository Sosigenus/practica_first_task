package practica.springstudentsz.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import practica.springstudentsz.dto.BookDTO;

@FeignClient(name = "book-service", url = "http://localhost:8081/api/v1/books")
public interface BookClient {
    @GetMapping("/{id}")
    BookDTO getBookById(@PathVariable Long id);
}
