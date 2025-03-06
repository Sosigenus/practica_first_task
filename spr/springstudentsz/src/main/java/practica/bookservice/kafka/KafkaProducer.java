package practica.bookservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import practica.bookservice.event.BookEvent;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, BookEvent> kafkaTemplate;

    public void sendBookNotification(BookEvent event) {
        kafkaTemplate.send("book-notifications", event);
    }
}