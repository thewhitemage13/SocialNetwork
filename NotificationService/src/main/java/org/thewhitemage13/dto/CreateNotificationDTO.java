package org.thewhitemage13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateNotificationDTO {
    private Long userId;
    private String type;
    private String message;

    @Override
    public String toString() {
        return "CreateNotificationDTO{" +
                "userId=" + userId +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
