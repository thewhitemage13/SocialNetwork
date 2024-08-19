package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.PostEvent;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.interfaces.PostDeleteEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "post.deleted")
public class PostDeletedEventHandler implements PostDeleteEventHandlerInterface {
    private final NotificationService notificationService;

    @Autowired
    public PostDeletedEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @KafkaHandler
    public void postDelete(PostEvent postEvent) {
        notificationService.createNotification(new CreateNotificationDTO(postEvent.getUserId(), "SMS", "Your post with id = %s was deleted".formatted(postEvent.getPostId())));
    }
}
