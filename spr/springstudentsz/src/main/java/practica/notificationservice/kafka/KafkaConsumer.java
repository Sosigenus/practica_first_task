package practica.notificationservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import practica.notificationservice.event.BookEvent;

@Slf4j
@Service
public class KafkaConsumer {
    @KafkaListener(topics = "book-notifications", groupId = "notification-group")
    public void listen(BookEvent event) {
        log.info("Получено уведомление: {}", event.getMessage());
    }
}
