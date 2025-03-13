package book_service.kafka;

import book_service.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
//@Service
//@RequiredArgsConstructor
@Component
public class KafkaProducerService {

    private final KafkaTemplate<String, Book> kafkaTemplate;

    @Autowired
    KafkaProducerService(KafkaTemplate<String, Book> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String topic, Book book) {
        log.info("Отправка сообщения в Kafka. Топик: {}, Сообщение: {}", topic, book);
        kafkaTemplate.send(topic, book).whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Сообщение успешно отправлено в Kafka: {}", result.getProducerRecord());
            } else {
                log.error("Ошибка отправки сообщения в Kafka", ex);
            }
        });
    }
}
