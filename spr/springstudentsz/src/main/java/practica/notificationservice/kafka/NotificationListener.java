package practica.notificationservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import practica.notificationservice.dto.NotificationDTO;

@Service
@Slf4j
public class NotificationListener {
    @KafkaListener(topics = "notifications-topic", groupId = "notification-group")
    public void listen(NotificationDTO notification) {
        log.info("📩 Уведомление: студент {} запросил книгу '{}'",
                notification.getStudentEmail(), notification.getBookTitle());
    }
}
