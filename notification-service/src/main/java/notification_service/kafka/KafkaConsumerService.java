package notification_service.kafka;

import lombok.extern.slf4j.Slf4j;
import notification_service.model.Book;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
public class KafkaConsumerService {

    @KafkaListener(topics = "add_book", groupId = "notification-group")
    void listen(Book book) {
        log.info("Получено уведомление: {}", book);
        System.out.println(book);
        // Здесь можно отправить e-mail или push-уведомление
    }
}
