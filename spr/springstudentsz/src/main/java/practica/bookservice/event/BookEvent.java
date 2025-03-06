package practica.bookservice.event;

import lombok.Data;

@Data
public class BookEvent {
    private Long bookId;
    private String message;
}