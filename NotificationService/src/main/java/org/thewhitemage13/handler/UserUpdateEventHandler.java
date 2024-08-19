package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.interfaces.UserUpdatedEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "user.updated")
public class UserUpdateEventHandler implements UserUpdatedEventHandlerInterface {
    private final NotificationService notificationService;

    @Autowired
    public UserUpdateEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @KafkaHandler
    public void userUpdated(UserEvent userEvent) {
        notificationService.createNotification(new CreateNotificationDTO(userEvent.getUserId(), "SMS", "User updated"));
    }
}
