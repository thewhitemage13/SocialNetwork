package org.thewhitemage13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetNotificationDTO {
    private String type;
    private String message;
    private boolean read;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "GetNotificationDTO{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", read=" + read +
                ", createdAt=" + createdAt +
                '}';
    }
}
