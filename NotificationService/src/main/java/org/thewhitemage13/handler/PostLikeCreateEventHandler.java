package org.thewhitemage13.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.LikeEvent;
import org.thewhitemage13.clients.PostClient;
import org.thewhitemage13.dto.CreateNotificationDTO;
import org.thewhitemage13.interfaces.LikePostCreateEventHandlerInterface;
import org.thewhitemage13.service.NotificationService;

@Component
@KafkaListener(topics = "post.like.created")
public class PostLikeCreateEventHandler implements LikePostCreateEventHandlerInterface {
    private final NotificationService notificationService;
    private final PostClient postClient;

    @Autowired
    public PostLikeCreateEventHandler(NotificationService notificationService, PostClient postClient) {
        this.notificationService = notificationService;
        this.postClient = postClient;
    }

    @Override
    @KafkaHandler
    public void likePostCreate(LikeEvent likeEvent) {
        Long user = postClient.getUserIdByPostId(likeEvent.getPostId()).getBody();

        notificationService.createNotification
                (new CreateNotificationDTO(user ,"SMS", "User with id = %s like your post with id = %s"
                        .formatted(likeEvent.getUserId(), likeEvent.getPostId())));
    }
}
