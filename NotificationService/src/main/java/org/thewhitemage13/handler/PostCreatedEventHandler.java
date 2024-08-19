package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.PostEvent;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.interfaces.PostCreateEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "post.created")
public class PostCreatedEventHandler implements PostCreateEventHandlerInterface {
    private final NotificationService notificationService;

    @Autowired
    public PostCreatedEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @KafkaHandler
    public void postCreate(PostEvent postEvent) {
        notificationService.createNotification(new CreateNotificationDTO(postEvent.getUserId(), "SMS", "Your post with id = %s is up".formatted(postEvent.getPostId())));
    }
}
