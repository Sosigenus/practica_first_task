package practica.bookservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import practica.bookservice.dto.NotificationDTO;

@Service
@RequiredArgsConstructor
public class BookEventPublisher {
    private final KafkaTemplate<String, NotificationDTO> kafkaTemplate;

    public void sendNotification(NotificationDTO notification) {
        kafkaTemplate.send("notifications-topic", notification);
    }
}
