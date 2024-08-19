package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.UserEvent;
import org.thewhitemage13.interfaces.UserDeletedEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "user.deleted")
public class UserDeletedEventHandler implements UserDeletedEventHandlerInterface {
    private final NotificationService notificationService;

    @Autowired
    public UserDeletedEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @KafkaHandler
    public void userDeleted(UserEvent userEvent) {
        notificationService.deleteAllByUserId(userEvent.getUserId());
    }
}
