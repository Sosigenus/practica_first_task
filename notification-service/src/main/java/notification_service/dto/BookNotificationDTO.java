package notification_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookNotificationDTO {
    private String title;
    private String author;
    private String message;
}
