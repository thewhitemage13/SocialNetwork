package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.CommentEvent;
import org.thewhitemage13.clients.PostClient;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.interfaces.CommentCreateEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "comment.created")
public class CommentCreateEventHandler implements CommentCreateEventHandlerInterface {
    private final NotificationService notificationService;
    private final PostClient postClient;

    @Autowired
    public CommentCreateEventHandler(NotificationService notificationService, PostClient postClient) {
        this.notificationService = notificationService;
        this.postClient = postClient;
    }

    @Override
    @KafkaHandler
    public void commentCreated(CommentEvent commentEvent) {
        Long user = postClient.getUserIdByPostId(commentEvent.getPostId()).getBody();

        notificationService
                .createNotification
                        (new CreateNotificationDTO
                                (user, "SMS", "User with id = %s left a comment under your post with id = %s"
                                        .formatted(commentEvent.getUserId(), commentEvent.getPostId())));
    }

}
