package notification_service.kafka;

import notification_service.dto.BookNotificationDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookKafkaConsumer {

    // Метод для получения уведомлений из Kafka
    @KafkaListener(topics = "book-notifications", groupId = "notification-group")
    public void consumeNotification(BookNotificationDTO notificationDTO) {
        // Логика обработки уведомления, например, сохранение в базе данных уведомлений
        System.out.println("Received notification: " + notificationDTO.getMessage());
        // Дополнительные действия, такие как сохранение уведомления в базе данных
        System.out.println("Получено уведомление из Kafka:");
        System.out.println("Название: " + notificationDTO.getTitle());
        System.out.println("Автор: " + notificationDTO.getAuthor());
        System.out.println("Сообщение: " + notificationDTO.getMessage());
    }
}
