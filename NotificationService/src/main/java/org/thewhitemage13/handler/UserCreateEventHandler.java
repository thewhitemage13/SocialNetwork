package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.interfaces.UserCreatedEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "user.created")
public class UserCreateEventHandler implements UserCreatedEventHandlerInterface {
    private final NotificationService notificationService;

    @Autowired
    public UserCreateEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @KafkaHandler
    public void userCreated(UserEvent userEvent) {
        notificationService.createNotification(new CreateNotificationDTO(userEvent.getUserId(), "SMS", "User created"));
    }
}
