package book_service.kafka;

import book_service.dto.BookNotificationDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookKafkaProducer {

    private final KafkaTemplate<String, BookNotificationDTO> kafkaTemplate;

    // Название топика, в который будут отправляться уведомления
    private static final String NOTIFICATION_TOPIC = "book-notifications";

    public BookKafkaProducer(KafkaTemplate<String, BookNotificationDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Метод для отправки уведомлений
    public void sendNotification(BookNotificationDTO notificationDTO) {
        kafkaTemplate.send(NOTIFICATION_TOPIC, "new-book", notificationDTO);
        System.out.println("Notification sent to " + notificationDTO);
    }
}